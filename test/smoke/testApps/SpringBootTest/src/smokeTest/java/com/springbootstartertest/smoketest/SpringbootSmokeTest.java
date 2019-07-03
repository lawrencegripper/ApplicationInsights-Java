package com.springbootstartertest.smoketest;

import java.util.List;
import java.util.Map;

import com.microsoft.applicationinsights.internal.schemav2.Envelope;
import com.microsoft.applicationinsights.internal.schemav2.EventData;
import com.microsoft.applicationinsights.internal.schemav2.RemoteDependencyData;
import com.microsoft.applicationinsights.internal.schemav2.RequestData;
import com.microsoft.applicationinsights.smoketest.AiSmokeTest;
import com.microsoft.applicationinsights.smoketest.TargetUri;
import com.microsoft.applicationinsights.smoketest.UseAgent;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Test;

import static org.hamcrest.Matchers.hasItem;
import static org.junit.Assert.*;

@UseAgent
public class SpringbootSmokeTest extends AiSmokeTest {

    @Test
    @TargetUri("/throwsException")
    public void testResultCodeWhenRestControllerThrows() {
        assertEquals(1, mockedIngestion.getCountForType("RequestData"));
        List<Envelope> exceptionEnvelopeList = mockedIngestion.getItemsEnvelopeDataType("ExceptionData");
        assertEquals(1, exceptionEnvelopeList.size());

        Envelope exceptionEnvelope = exceptionEnvelopeList.get(0);
        RequestData d = getTelemetryDataForType(0, "RequestData");
        String requestOperationId = d.getId();
        assertTrue(requestOperationId.contains(exceptionEnvelope.getTags().
                getOrDefault("ai.operation.id", null)));
    }

    @Test
    @TargetUri("/asyncDependencyCallWithApacheHttpClient4")
    public void testAsyncDependencyCallWithApacheHttpClient4() {
        assertEquals(1, mockedIngestion.getCountForType("RequestData"));
        assertEquals(1, mockedIngestion.getCountForType("RemoteDependencyData"));
        RequestData d = getTelemetryDataForType(0, "RequestData");
        RemoteDependencyData rdd = getTelemetryDataForType(0, "RemoteDependencyData");
        String requestOperationId = d.getId();
        String rddId = rdd.getId();
        assertTrue(rddId.contains(requestOperationId));
    }

    @Test
    @TargetUri("/asyncDependencyCallWithApacheHttpClient3")
    public void testAsyncDependencyCallWithApacheHttpClient3() {
        assertEquals(1, mockedIngestion.getCountForType("RequestData"));
        assertEquals(1, mockedIngestion.getCountForType("RemoteDependencyData"));
        RequestData d = getTelemetryDataForType(0, "RequestData");
        RemoteDependencyData rdd = getTelemetryDataForType(0, "RemoteDependencyData");
        String requestOperationId = d.getId();
        String rddId = rdd.getId();
        assertTrue(rddId.contains(requestOperationId));
    }

    @Test
    @TargetUri("/asyncDependencyCallWithOkHttp3")
    public void testAsyncDependencyCallWithOkHttp3() {
        assertEquals(1, mockedIngestion.getCountForType("RequestData"));
        assertEquals(1, mockedIngestion.getCountForType("RemoteDependencyData"));
        RequestData d = getTelemetryDataForType(0, "RequestData");
        RemoteDependencyData rdd = getTelemetryDataForType(0, "RemoteDependencyData");
        String requestOperationId = d.getId();
        String rddId = rdd.getId();
        assertTrue(rddId.contains(requestOperationId));
    }

    @Test
    @TargetUri("/asyncDependencyCallWithOkHttp2")
    public void testAsyncDependencyCallWithOkHttp2() {
        assertEquals(1, mockedIngestion.getCountForType("RequestData"));
        assertEquals(1, mockedIngestion.getCountForType("RemoteDependencyData"));
        RequestData d = getTelemetryDataForType(0, "RequestData");
        RemoteDependencyData rdd = getTelemetryDataForType(0, "RemoteDependencyData");
        String requestOperationId = d.getId();
        String rddId = rdd.getId();
        assertTrue(rddId.contains(requestOperationId));
    }

    @Test
    @TargetUri("/asyncDependencyCallWithHttpURLConnection")
    public void testAsyncDependencyCallWithHttpURLConnection() {
        assertEquals(1, mockedIngestion.getCountForType("RequestData"));
        assertEquals(1, mockedIngestion.getCountForType("RemoteDependencyData"));
        RequestData d = getTelemetryDataForType(0, "RequestData");
        RemoteDependencyData rdd = getTelemetryDataForType(0, "RemoteDependencyData");
        String requestOperationId = d.getId();
        String rddId = rdd.getId();
        assertTrue(rddId.contains(requestOperationId));
    }
}
