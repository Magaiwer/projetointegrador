package projetointegrador.fileTransfer;

import lombok.Getter;
import lombok.Setter;

import java.io.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.Socket;
import java.time.LocalDateTime;

@Getter
@Setter
public class FileClient implements Serializable {

    private static final long serialVersionUID = 1L;

    private String name;
    private byte[] content;
    private transient double size;
    private LocalDateTime dateTimeTransfer;
    private transient String ipServer;
    private transient String port;
    private String outputDir;

    private Socket socket;

    public FileClient() {
        this.dateTimeTransfer = LocalDateTime.now();
    }


    public void sendFile(Socket socket) throws IOException {
        BufferedOutputStream bf = new BufferedOutputStream(socket.getOutputStream());

        byte[] byteSerialized = serialize();
        ByteArrayInputStream inputStream = new ByteArrayInputStream(byteSerialized);
        byte[] buffer = new byte[byteSerialized.length];

        int count;
        while ((count = inputStream.read(buffer, 0, buffer.length)) != -1) {
            bf.write(buffer, 0, count);
            System.out.println("enviando" + count);
        }
        System.out.println("Arquivo enviado");
        bf.flush();
        bf.close();
    }

    public void convertBytes(File file) {
        FileInputStream fileInputStream;
        byte[] bFile = new byte[(int) file.length()];

        try {
            System.out.println(bFile.length);
            fileInputStream = new FileInputStream(file);
            fileInputStream.read(bFile);
            fileInputStream.close();
            System.out.println(bFile.length);
            this.setContent(bFile);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getSizeMB() {
        BigDecimal mb = new BigDecimal(1024 * 1024);
        return new BigDecimal(this.size)
                .divide(mb)
                .setScale(2, RoundingMode.HALF_EVEN).toString();
    }

    private byte[] serialize() {
        try {
            ByteArrayOutputStream bao = new ByteArrayOutputStream();
            ObjectOutputStream ous;
            ous = new ObjectOutputStream(bao);
            ous.writeObject(this);
            return bao.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
