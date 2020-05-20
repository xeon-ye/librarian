package com.nulijiushimeili.librarianwebui.utils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.avro.Schema;
import org.apache.avro.generic.GenericData;
import org.apache.avro.generic.GenericDatumWriter;
import org.apache.avro.generic.GenericRecord;
import org.apache.avro.io.BinaryDecoder;
import org.apache.avro.io.BinaryEncoder;
import org.apache.avro.io.DecoderFactory;
import org.apache.avro.io.EncoderFactory;
import org.apache.avro.specific.SpecificDatumReader;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Map;


public class AvroFactory {

    /**
     * 对象转avro字节数组
     * @param schemaStr avro schema
     * @param data 需要转换的对象
     * @return avro字节数组
     */
    public static byte[] mapToByte(String schemaStr, Map<String, Object> data) {
        try (ByteArrayOutputStream out = new ByteArrayOutputStream(1024 * 2 * 1024)) {
            Schema schema = new Schema.Parser().parse(schemaStr);
            GenericRecord record = new GenericData.Record(schema);

            GenericDatumWriter<GenericRecord> writer = new GenericDatumWriter<>(schema);

            EncoderFactory encoderFactory = EncoderFactory.get();
            BinaryEncoder encoder = encoderFactory.binaryEncoder(out, null);

            writeDataToRecord(schemaStr, record, data);
            writer.write(record, encoder);
            encoder.flush();
            out.flush();

            return out.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * avro字节数组转record
     * @param schemaStr avro schema
     * @param byteData avro字节数组
     * @return record
     * @throws IOException decoder.isEnd()
     */
    public static GenericRecord byteToRecord(String schemaStr, byte[] byteData) throws IOException {
        Schema schema = new Schema.Parser().parse(schemaStr);
        GenericRecord record = new GenericData.Record(schema);

        SpecificDatumReader<GenericRecord> reader = new SpecificDatumReader<>(schema);
        BinaryDecoder decoder = DecoderFactory.get().binaryDecoder(byteData, null);
        while (!decoder.isEnd()) {
            reader.read(record, decoder);
        }
        return record;
    }

    /**
     * 将对象值写入record
     * @param schemaStr avro schema
     * @param record 需要写入的record
     * @param data 需要读取的对象
     */
    private static void writeDataToRecord(String schemaStr, GenericRecord record, Map<String, Object> data) {
        JSONObject jsonObject = JSONObject.parseObject(schemaStr);
        JSONArray fields = jsonObject.getJSONArray("fields");
        for (int i = 0; i < fields.size(); i++) {
            String name = fields.getJSONObject(i).getString("name");
            record.put(name, data.get(name));
        }
    }
}
