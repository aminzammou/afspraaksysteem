package nl.deketelman.project.persistence;

import com.azure.storage.blob.BlobClient;
import com.azure.storage.blob.BlobContainerClient;
import com.azure.storage.blob.BlobContainerClientBuilder;
import nl.deketelman.project.model.Afspraak;
//import nl.deketelman.project.model.AlleKlassen;
import nl.deketelman.project.model.Bedrijf;

import java.io.*;
import java.util.ArrayList;

public class PersistenceManager {
    private final static String ENDPOINT = "https://ironamin.blob.core.windows.net/";
    private final static String SASTOKEN = "?sv=2019-10-10&ss=b&srt=sco&sp=rwdlacx&se=2020-07-30T02:22:13Z&st=2020-06-26T18:22:13Z&spr=https&sig=vXtfx00cvsUQDFtzY44gIyStJYShrOpM%2FpSvML5PCys%3D";
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

                Bedrijf bedrijf = (Bedrijf) ois.readObject();
                Bedrijf.setBedrijf(bedrijf);

                baos.close();
                ois.close();
            }
        }
    }
    public static void saveWorldToAzure() throws IOException {

        if (!blobContainer.exists()){
            blobContainer.create();
        }
        BlobClient blob = blobContainer.getBlobClient("afsprakenblob");
        Bedrijf alleKlassen = Bedrijf.getAlles();
//        AlleKlassen alleKlassen = new AlleKlassen();

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream ois = new ObjectOutputStream(baos);
        ois.writeObject(alleKlassen);

        byte[] bytez = baos.toByteArray();

        ByteArrayInputStream bais = new ByteArrayInputStream(bytez);
        blob.upload(bais, bytez.length, true);
    }
}
