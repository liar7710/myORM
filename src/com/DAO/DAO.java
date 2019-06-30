package com.DAO;

import java.util.List;

public interface DAO<T> {
    public boolean add(T u);//单行添加
    public boolean add(List<T> u);//多行添加
    public boolean delete(Object ...id);//多条件查群，列名+列值，可以无限多:delete(1,"username",2,"password")
    public T Query(int col, Object id);//单个查询，列数+值，Query(1,"123")
    public List<T> QueryAll(Object ...id);//多条件查群，列名+列值，可以无限多:QueryAll(1,"123",1,"123")
    public boolean alter(int num,T u);//修改，num是指定表的前num列为查询语句，u为所修改的表行
    public List<T> queryByNew();
    public List<T> queryByHot();
    public List<T> QueryForPage(int pageNo, int pageSize, Object... id);
    public int queryForCount();
    public int queryForCount(int state);
    public boolean freeze(String username);
    public boolean unfreeze(String username);
}