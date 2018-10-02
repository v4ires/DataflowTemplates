package net.orfeon.cloud.dataflow.spanner;

import com.google.cloud.spanner.Struct;
import com.google.cloud.spanner.Value;
import org.apache.beam.sdk.Pipeline;
import org.apache.beam.sdk.testing.PAssert;
import org.apache.beam.sdk.transforms.Create;
import org.apache.beam.sdk.transforms.ParDo;
import org.apache.beam.sdk.values.PCollection;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class StructToJsonDoFnTest {

    @Before
    public void setUp() {
        System.out.println("setup");
    }

    @After
    public void tearDown() {
        System.out.println("teardown");
    }

    @Test
    public void test() {
        Struct struct1 = Struct.newBuilder()
                .add("bool", Value.bool(true))
                .add("int", Value.int64(12))
                .add("string", Value.string("string"))
                .add("float", Value.float64(10.12))
                //.add("", Value.timestamp(Timestamp.from()))
                .build();
        Struct struct2 = Struct.newBuilder()
                .add("bool", Value.bool(false))
                .add("int", Value.int64(-10))
                .add("string", Value.string("this is a pen!"))
                .add("float", Value.float64(0.12))
                //.add("", Value.timestamp(Timestamp.from()))
                .build();

        Pipeline pipeline = Pipeline.create();
        PCollection<String> lines = pipeline
                .apply("CreateDummy", Create.of(struct1, struct2))
                .apply("ConvertToJson", ParDo.of(new StructToJsonDoFn()));

        PAssert.that(lines).containsInAnyOrder(
                "{\"bool\":true,\"int\":12,\"string\":\"string\",\"float\":10.12}",
                "{\"bool\":false,\"int\":-10,\"string\":\"this is a pen!\",\"float\":0.12}");

        pipeline.run();
    }

}