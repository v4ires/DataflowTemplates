package net.orfeon.cloud.dataflow.util.converter;

import com.google.cloud.spanner.Struct;
import org.junit.Assert;
import org.junit.Test;

public class StructAndJsonConverterTest {

    @Test
    public void test() {
        Struct struct = ConverterDataSupplier.createNestedStruct(false);
        String json = StructToJsonConverter.convert(struct);
        Assert.assertEquals("{\"bf\":false,\"if\":-12,\"ff\":110.005,\"sf\":\"I am a pen\",\"df\":\"2018-10-01\",\"tf\":\"2018-10-01T03:00:00Z\",\"nf\":null,\"lnf\":null,\"dnf\":null,\"tnf\":null,\"rf\":{\"cbf\":true,\"cif\":12,\"cff\":0.005,\"cdf\":\"2018-09-01\",\"ctf\":\"2018-09-01T03:00:00Z\",\"csf\":\"This is a pen\"},\"arf\":[{\"cbf\":true,\"cif\":12,\"cff\":0.005,\"cdf\":\"2018-09-01\",\"ctf\":\"2018-09-01T03:00:00Z\",\"csf\":\"This is a pen\"}],\"asf\":[\"a\",\"b\",\"c\"],\"aif\":[1,2,3],\"adf\":[\"2018-09-01\",\"2018-10-01\"],\"anf\":null,\"amf\":[1,2,3],\"atf\":[\"2018-09-01T03:00:00Z\",\"2018-10-01T03:00:00Z\"]}", json);
    }
}
