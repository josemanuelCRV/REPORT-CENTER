package com.labs.josemanuel.reportcenter;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.labs.josemanuel.reportcenter.Model.PropuestaDAO;
import com.labs.josemanuel.reportcenter.Repository.ProposalContract;
import com.labs.josemanuel.reportcenter.Repository.ProposalDbHelper;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.Shadows;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowApplication;

import java.io.File;
import java.io.IOException;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

/**
 * Created by Miguel on 21-09-16.
 */
@Config(constants = BuildConfig.class, sdk = 21, manifest = "/src/main/AndroidManifest.xml")
@RunWith(RobolectricGradleTestRunner.class)
public class DatabaseTest {


    private ProposalDbHelper dbHelper;


    @Before
    public void setUp() {
        ShadowApplication context = Shadows.shadowOf(RuntimeEnvironment.application);
        dbHelper = new ProposalDbHelper(context.getApplicationContext());
    }

    @Test
    public void whenTheDBHelperIsCreatedThenTheDatabaseNameShouldBeSet() {
        ShadowApplication context = Shadows.shadowOf(RuntimeEnvironment.application);
        dbHelper = new ProposalDbHelper(context.getApplicationContext());
        assertEquals(ProposalDbHelper.DBname, dbHelper.getDatabaseName());
    }

    @Test
    public void whenTheDBHelperIsCreatedThenTheProposalTableShouldBeCreated() {
        //Here ReminderContract.Reminders.DESCRIPTION is a column name in the database.
        //context= Shadows.shadowOf(RuntimeEnvironment.application);
        ContentValues proposalValues = new ContentValues();
        proposalValues.put(ProposalContract.Reminders.columnTitle, "Creación de propuesta desde POST");

        assertEquals(1, insertReminderValues(proposalValues));

    }

    @Test
    public void shouldBeAbleToReadValues() {
        String[] todo = {ProposalContract.Reminders.columnTitle};
        SQLiteDatabase readableDatabase = dbHelper.getReadableDatabase();

        //Here ReminderContract.Reminders.PROJECT_ALL is an array of Strings representing the database columns we need
        readableDatabase.beginTransaction();
        Cursor cursor = readableDatabase.rawQuery("SELECT * FROM " + ProposalDbHelper.tableName, null);
        readableDatabase.close();

        assertNotNull(cursor);
    }


    @Test
    public void shouldBeAbleToReadFile(){
        ObjectMapper mapper = new ObjectMapper();
        mapper.
        PropuestaDAO propuestaDAO=null;
        try {
            //Convert object to JSON string and save into file directly
            propuestaDAO= mapper.readValue(new File("C:\\Users\\Miguel\\Desktop\\Android PFC\\propuestaMock.json"), PropuestaDAO.class);
            System.out.println(propuestaDAO.toString());
        } catch (JsonGenerationException e) {
            e.printStackTrace();
        } catch (JsonParseException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        assertNotNull(propuestaDAO);
    }


    private Object mapIntoObject(JsonNode node) throws IOException,
            JsonProcessingException {
        JsonParser parser = node.traverse();
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(parser, wrappedType);
    }
    private long insertReminderValues(ContentValues reminderValues) {
        SQLiteDatabase writableDatabase = dbHelper.getWritableDatabase();
        long ack = writableDatabase.insert(ProposalDbHelper.tableName, null, reminderValues);

        if (ack != -1) {
            return 1;
        } else {
            return -1;
        }
    }

}
