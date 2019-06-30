package com.DAOlmpl;

import com.DAO.DAO;
import com.DAOFactory.DAOFactory;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DAOlmpl<T> implements DAO<T> {
    private final Field[] F;
    private final Method[] M;
    private final Class<T> C;
    private String table;

    public DAOlmpl(Class<T> s) {
        this.C = s;
        this.F = s.getDeclaredFields();
        this.M=s.getDeclaredMethods();
        for (Field i : F) {
            i.setAccessible(true);
        }
        try {
            table = (String) F[0].get(C.getDeclaredConstructor().newInstance());
        } catch (IllegalAccessException | InstantiationException | InvocationTargetException | NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean add(T u) {
        StringBuilder sql = new StringBuilder(String.format("insert into %s values (?", table));
        for (int i = 2; i < F.length; i++)
            sql.append(",?");
        sql.append(")");
        boolean flag = false;
        try (Connection conn = DAOFactory.getConnection();
             PreparedStatement pst = conn.prepareStatement(sql.toString())
        ) {
            for (int i = 1; i < F.length; i++) {
                pst.setObject(i, F[i].get(u));
            }
            flag = pst.execute();
        } catch (SQLException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return flag;
    }

    @Override
    public boolean add(List<T> u) {
        boolean flag = false;
        StringBuilder sql = new StringBuilder(String.format("insert into %s values (null", table));
        for (int i = 2; i < F.length; i++)
            sql.append(",?");
        sql.append(")");
        try (Connection conn = DAOFactory.getConnection();
        ) {
            conn.setAutoCommit(false);
            PreparedStatement pst = conn.prepareStatement(sql.toString());
            for (T p : u) {
                for (int i = 2; i < F.length; i++) {
                    pst.setObject(i - 1, F[i].get(p));
                }
                pst.addBatch();
            }
            pst.executeBatch();
            conn.commit();
            conn.setAutoCommit(true);
            flag = true;
        } catch (SQLException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return flag;
    }

    @Override
    public boolean delete(Object... id) {
        StringBuilder sql = new StringBuilder(String.format("delete from %s where %s=?", table, F[(int) id[0]].getName()));
        for (int i = 2; i < id.length; i += 2) {
            sql.append(String.format(" and %s=?", F[(int) id[i]].getName()));
        }
        boolean flag = false;
        try (Connection conn = DAOFactory.getConnection();
             PreparedStatement pst = conn.prepareStatement(sql.toString());) {
            for (int i = 1; i < id.length; i += 2) {
                pst.setObject((i + 1) / 2, id[i]);
            }
            flag = pst.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return flag;
    }

    @Override
    public T Query(int col, Object id) {
        List<T> L=QueryAll(col,id);
        return L.size()>0?L.get(0):null;
    }

    @Override
    public List<T> QueryAll(Object... id) {
        StringBuilder sql = new StringBuilder(String.format("select * from %s", table));
        if(id.length>0){
            sql.append(String.format(" where %s=?", F[(int) id[0]].getName()));
            for (int i = 2; i < id.length; i += 2) {
                sql.append(String.format(" and %s=?", F[(int) id[i]].getName()));
            }
        }
        List<T> L = new ArrayList<>();
        try (Connection c = DAOFactory.getConnection();
             PreparedStatement st = c.prepareStatement(sql.toString());
        ) {
            if(id.length>0){
                for (int i = 1; i < id.length; i += 2) {
                    st.setObject((i + 1) / 2, id[i]);
                }
            }
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                T g = C.getDeclaredConstructor().newInstance();
                for (int i = 1; i < F.length; i++) {
                    F[i].set(g,rs.getObject(i));
                }
                L.add(g);
            }
        } catch (SQLException | NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
        return L;
    }

    @Override
    public boolean alter(int num, T u) {
        StringBuilder sql = new StringBuilder(String.format("update %s set ", table));
        for (int i = 1 + num; i < F.length - 1; i++) {
            sql.append(F[i].getName()).append("=?,");
        }
        sql.append(F[F.length - 1].getName()).append("=? where ");
        for (int i = 1; i < num; i++) {
            sql.append(F[i].getName()).append("=?").append(" and ");
        }
        sql.append(F[num].getName()).append("=?");
        boolean flag = false;
        try (Connection conn = DAOFactory.getConnection();
             PreparedStatement pst = conn.prepareStatement(sql.toString());) {
            for (int i = 1 + num; i < F.length; i++) {
                pst.setObject(i - num, F[i].get(u));
            }
            for (int i = 1; i <= num; i++) {
                pst.setObject(F.length - (num - i + 1), F[i].get(u));
            }
            flag = pst.execute();
        } catch (SQLException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return flag;
    }

    public List<T> queryByNew() {
        String sql = String.format("select * from %s where status=1 order by id desc limit 0,10 ", table);
        List<T> plist = new ArrayList<>();
        try (Connection conn = DAOFactory.getConnection();
             PreparedStatement pst = conn.prepareStatement(sql);
             ResultSet rs = pst.executeQuery()) {
            while (rs.next()) {
                T t = C.getDeclaredConstructor().newInstance();
                for (int i = 1; i < F.length; i++) {
                    if(i==9)System.out.println(rs.getObject(i).getClass().getName());
                    F[i].set(t, rs.getObject(i));
                }
                plist.add(t);
            }
        } catch (SQLException | InstantiationException | InvocationTargetException | NoSuchMethodException
                | IllegalAccessException e) {
            e.printStackTrace();
        }
        return plist;
    }

    public List<T> queryByHot() {
        String sql = "select id, name, p.price, category_1, category_2, pnum, imgurl, status, description from products p left join orderitem o on p.id=o.product_id where status>=1 group by id order by sum(num) desc limit 10 ";
        List<T> plist = new ArrayList<>();
        try (Connection conn = DAOFactory.getConnection();
             PreparedStatement pst = conn.prepareStatement(sql);
             ResultSet rs = pst.executeQuery()) {
            while (rs.next()) {
                T t = C.getDeclaredConstructor().newInstance();
                for (int i = 1; i < F.length; i++) {
                    F[i].set(t, rs.getObject(i));
                }
                plist.add(t);
            }
        } catch (SQLException | InstantiationException | InvocationTargetException | NoSuchMethodException
                | IllegalAccessException e) {
            e.printStackTrace();
        }
        return plist;
    }
    public List<T> QueryForPage(int pageNo, int pageSize, Object... id) {
        StringBuilder sql = new StringBuilder(String.format("select * from %s", table));
        if(id.length>0){
            sql.append(String.format(" where %s=?",F[(int)id[0]].getName()));
            for (int i = 2; i < id.length; i += 2) {
                sql.append(String.format(" and %s=?", F[(int) id[i]].getName()));
            }
        }
        sql.append(String.format(" limit %d,%d", (pageNo - 1) * pageSize, pageSize));
        List<T> L = new ArrayList<>();
        try (Connection c = DAOFactory.getConnection();
             PreparedStatement st = c.prepareStatement(sql.toString());
        ) {
            if(id.length>0){
                for (int i = 1; i < id.length; i += 2) {
                    st.setObject((i + 1) / 2, id[i]);
                }
            }
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                T g = C.getDeclaredConstructor().newInstance();
                for (int i = 1; i < F.length; i++) {
                    F[i].set(g, rs.getObject(i));
                }
                L.add(g);
            }
        } catch (SQLException | NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
        return L;
    }

    public int queryForCount() {
        String sql = String.format("select count(*) from %s", table);
        try (
                Connection conn = DAOFactory.getConnection();
                PreparedStatement pst = conn.prepareStatement(sql);
                ResultSet rs = pst.executeQuery()
        ) {
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int queryForCount(int state) {
        StringBuffer sb = new StringBuffer(String.format("select count(*) from %s", table));
        sb.append(String.format(" where paystate = %d", state));
        try (Connection conn = DAOFactory.getConnection();
             Statement pst = conn.createStatement();
             ResultSet rs = pst.executeQuery(sb.toString())) {
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public boolean freeze(String username) {
        String sql = "update users set state = 0 where username = ?";
        try (Connection conn = DAOFactory.getConnection();
             PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setString(1, username);
            pst.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean unfreeze(String username) {
        String sql = "update users set state = 1 where username = ?";
        try (Connection conn = DAOFactory.getConnection();
             PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setString(1, username);
            pst.executeUpdate();
            return true;
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return false;
    }
}