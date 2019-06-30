package com.DAOlmpl;

import com.Bean.addr;
import com.Bean.file_upload;
import com.Bean.users;
import com.mysql.cj.jdbc.Blob;
import com.mysql.cj.jdbc.Clob;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.nio.file.Path;
import java.sql.Timestamp;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DAOlmplTest {
    /*
    @Test
    void query() {
        addr u=new addr();
        u.setAddress("123");
        u.setConsignee("127");
        u.setTel("123");
        u.setU_name("123");
        DAOlmpl<addr> dao=new DAOlmpl<>(addr.class);
        dao.add(u);
        List<addr> l=dao.QueryAll(1,"125",2,"123");
        for(addr i:l)
            System.out.println(i.getU_name());
        dao.delete(1,"125",2,"123");
        for(addr i:l)
            System.out.println(i.getU_name());
    }
    */
    @Test
    void query_1() throws IOException {
        DAOlmpl<file_upload> fdao=new DAOlmpl<>(file_upload.class);
        /*
        file_upload f=new file_upload();
        File nf=new File("C:\\Users\\77105\\Desktop\\download.jpg");
        InputStream is=new BufferedInputStream(new FileInputStream(nf));
        f.setBinanry(is);
        fdao.add(f);
        */
        file_upload f=fdao.Query(1,"1");
        FileOutputStream fo=new FileOutputStream("C:\\Users\\77105\\Desktop\\1.jpg");
        fo.write(f.getBinanry());
    }
}