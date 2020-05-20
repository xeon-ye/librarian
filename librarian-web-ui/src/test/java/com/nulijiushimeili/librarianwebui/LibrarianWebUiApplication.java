package com.nulijiushimeili.librarianwebui;


import com.google.gson.Gson;
import com.nulijiushimeili.librarianwebui.utils.AvroFactory;
import lombok.extern.slf4j.Slf4j;
import org.apache.avro.generic.GenericRecord;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class LibrarianWebUiApplication {



    @Test
    public void test() throws IOException {
        String schema = "{" +
                "   \"type\": \"record\"," +
                "   \"name\": \"user\"," +
                "   \"fields\": [" +
                "       {" +
                "           \"name\": \"name\"," +
                "           \"type\": [\"null\", \"string\"]" +
                "       }," +
                "       {" +
                "           \"name\": \"age\"," +
                "           \"type\": [\"null\", \"int\"]" +
                "       }," +
                "       {" +
                "           \"name\": \"gender\"," +
                "           \"type\": [\"null\", \"boolean\"]" +
                "       }," +
                "       {" +
                "           \"name\": \"info\"," +
                "           \"type\": [\"null\", \"bytes\"]" +
                "       }," +
                "       {" +
                "           \"name\": \"info2\"," +
                "           \"type\": [\"null\", \"string\"]" +
                "       }" +
                "   ]" +
                "}";
        Map<String, Object> data = new HashMap<>();
        data.put("name", "xiaoming");
        data.put("age", 18);
        data.put("gender", true);
        data.put("info", ByteBuffer.wrap(new byte[] {1,2,3}));
        data.put("info2", null);
        byte[] bytes = AvroFactory.mapToByte(schema, data);
        GenericRecord record = AvroFactory.byteToRecord(schema, bytes);
        System.out.println(Arrays.toString(bytes));
        System.out.println(record);
    }
}
