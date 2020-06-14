package nl.deketelman.project.persistence;

import com.azure.storage.blob.BlobClient;
import com.azure.storage.blob.BlobContainerClient;
import com.azure.storage.blob.BlobContainerClientBuilder;
import nl.deketelman.project.model.Afspraak;
import nl.deketelman.project.model.AlleKlassen;
import nl.deketelman.project.model.Bedrijf;

import java.io.*;
import java.util.ArrayList;

public class PersistenceManager {
    private final static String ENDPOINT = "https://ironamin.blob.core.windows.net/";
    private final static String SASTOKEN = "?sv=2019-10-10&ss=b&srt=sco&sp=rwdlacx&se=2020-06-01T01:49:29Z&st=2020-05-31T17:49:29Z&spr=https&sig=f0VayHO014xEIsqTGiulpMw3slA81fMNYaIm8T5%2FCCY%3D";
    private final static String CONTAINER = "afsprakencontainer";

    private static BlobContainerClient blobContainer = new BlobContainerClientBuilder().endpoint(ENDPOINT).sasToken(SASTOKEN).containerName(CONTAINER).buildClient();

    public static void loadWorldFromAzure() throws IOException, ClassNotFoundException {

        if (blobContainer.exists()){
            BlobClient blob = blobContainer.getBlobClient("afsprakenblob");

            if (blob.exists()){
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                blob.download(baos);

//                byte[] bytez = baos.toByteArray();

                ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
                ObjectInputStream ois = new ObjectInputStream(bais);

                Afspraak wereld = (Afspraak) ois.readObject();
//                World.setWorld(wereld);
                System.out.println(wereld);

                baos.close();
                ois.close();
            }
        }
    }
    public static void saveWorldToAzure() throws IOException {
//        ArrayList<Afspraak> bedrijf = Bedrijf.getalleafspraken();
        AlleKlassen alleKlassen = new AlleKlassen();
        if (!blobContainer.exists()){
            blobContainer.create();
        }
        BlobClient blob = blobContainer.getBlobClient("afsprakenblob");

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream ois = new ObjectOutputStream(baos);
        ois.writeObject(alleKlassen);

        byte[] bytez = baos.toByteArray();

        ByteArrayInputStream bais = new ByteArrayInputStream(bytez);
        blob.upload(bais, bytez.length, true);
    }
}
