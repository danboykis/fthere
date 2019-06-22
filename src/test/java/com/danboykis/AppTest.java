package com.danboykis;

import static org.junit.Assert.assertEquals;

import org.hl7.fhir.Patient;
import org.junit.Before;
import org.junit.Test;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.transform.stream.StreamSource;
import java.io.StringReader;
import java.nio.file.Files;
import java.nio.file.Paths;

public class AppTest {

    JAXBContext context;
    @Before
    public void befre() throws JAXBException {
        this.context = JAXBContext.newInstance(Patient.class);
    }

    @Test
    public void parsePatient() throws Exception {
        String xml = String.join( "\n", Files.readAllLines(Paths.get("src/test/resources/patient-example.xml")));
        System.out.println(xml);

        JAXBElement<Patient> pe = context.createUnmarshaller().unmarshal(new StreamSource(new StringReader(xml)), Patient.class);
        assertEquals("usual", pe.getValue().getIdentifier().get(0).getUse().getValue().value());
    }
}
