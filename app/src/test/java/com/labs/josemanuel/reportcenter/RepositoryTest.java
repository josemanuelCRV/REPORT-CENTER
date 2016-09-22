package com.labs.josemanuel.reportcenter;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.labs.josemanuel.reportcenter.Model.PropuestaDAO;
import com.labs.josemanuel.reportcenter.Repository.ProposalContract;
import com.labs.josemanuel.reportcenter.Repository.ProposalDTOout;
import com.labs.josemanuel.reportcenter.Repository.ProposalDbHelper;
import com.labs.josemanuel.reportcenter.Repository.ProposalVOin;
import com.labs.josemanuel.reportcenter.Repository.Repository;

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
import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.notNull;
import static org.mockito.Mockito.when;
/**
 * Created by Miguel on 22-09-16.
 */

@Config(constants = BuildConfig.class, sdk = 21, manifest = "/src/main/AndroidManifest.xml")
@RunWith(MockitoJUnitRunner.class)
public class RepositoryTest {

    private Repository repository;

    @Before
    public void setUp(){
        //repository=Repository.getRepository("mongodb://127.0.0.1:27017");
        repository=Repository.getRepository("mongodb://test:citymike2016@ds035816.mlab.com:35816/cityhack_db?apiKey=2E81PUmPFI84t7UIc_5YdldAp1ruUPKye");

    }
    @Test
    public void shouldBeAbleToGenerateARepositoryClass(){
        assertNotNull(repository);
    }

    @Test
    public void shouldBeAbleToGetProposals(){
        List<ProposalDTOout> proposals;
        proposals= repository.getProposals();
        assertEquals(3,proposals.size());
    }
    @Test
    public void shouldBeAbleToInsertProposal(){
        ProposalVOin proposalVOin = new ProposalVOin();
        proposalVOin.set_id(String.valueOf((int)Math.floor(Math.random()*10)));
        proposalVOin.setTitle("Hola hola desde Android!");
        int result = repository.insertProposal(proposalVOin);

        assertEquals(1,result);


    }
}
