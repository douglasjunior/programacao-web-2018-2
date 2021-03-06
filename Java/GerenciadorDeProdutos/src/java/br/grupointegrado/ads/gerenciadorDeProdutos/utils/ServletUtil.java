package br.grupointegrado.ads.gerenciadorDeProdutos.utils;

import br.grupointegrado.ads.gerenciadorDeProdutos.modelos.Arquivo;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.tomcat.util.http.fileupload.FileItem;
import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;
import org.apache.tomcat.util.http.fileupload.servlet.ServletRequestContext;

/**
 * Classe Útil responsável por agrupar métodos úteis referente aos Servlets
 */
public class ServletUtil {

    private static final int LIMITE_MEMORIA = 1024 * 1024 * 3;  // 3MB
    private static final int TAMANHO_MAXIMO_ARQUIVO = 1024 * 1024 * 40; // 5MB
    private static final int TAMANHO_MAXIMO_REQUEST = 1024 * 1024 * 50; // 10MB
    private static final String DIRETORIO_ARQUIVOS = "c:/sistema/arquivos"; // Altere esse diretório para o caminho de sua preferência

    static {
        // Cria o diretório para armazenar os arquivos
        File diretorio = new File(DIRETORIO_ARQUIVOS);
        if (!diretorio.exists()) {
            diretorio.mkdirs();
        }
    }

    /**
     * Recupera os parâmetros vindo de um requisição multipart.<br><br>
     * Isso ocorre quando o formulário HTML possui o atributo:<br>
     * enctype="multipart/form-data".
     *
     * @param req
     * @return
     * @throws FileUploadException
     * @throws IOException
     */
    public static Map<String, Object> recuperaParametrosMultipart(HttpServletRequest req) throws FileUploadException, IOException {
        // verifica se a requisição atual contem o Upload da foto
        if (!ServletFileUpload.isMultipartContent(req)) {
            return null;
        }
        // cria o Map que irá armazenar 
        Map<String, Object> parametros = new HashMap<>();

        // configura os atributos do upload
        DiskFileItemFactory factory = new DiskFileItemFactory();
        factory.setSizeThreshold(LIMITE_MEMORIA);
        factory.setRepository(new File(System.getProperty("java.io.tmpdir")));
        ServletFileUpload upload = new ServletFileUpload(factory);
        upload.setFileSizeMax(TAMANHO_MAXIMO_ARQUIVO);
        upload.setSizeMax(TAMANHO_MAXIMO_REQUEST);

        // recupera os parâmetros enviados pelo Form (incluindo o arquivo de upload)
        List<FileItem> formItems = upload.parseRequest(new ServletRequestContext(req));

        if (formItems != null && formItems.size() > 0) {
            // percorre todos os itens (input) vindos do formulário HTML
            for (FileItem item : formItems) {
                // verifica se o parâmetro é um campo do formulário ou um arquivo de upload
                if (!item.isFormField()) {
                    // se for um arquivo de upload então lê os bytes
                    InputStream is = item.getInputStream();
                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
                    try {
                        System.out.println(item.getName());
                        IOUtils.copy(is, baos);
                        byte[] bytesArquivo = baos.toByteArray();
                        Arquivo arquivo = new Arquivo();
                        arquivo.setNome(item.getName());
                        arquivo.setBytes(bytesArquivo);
                        // salva o arquivo de upload junto com os parâmetros
                        parametros.put(item.getFieldName(), arquivo);
                    } finally {
                        if (is != null) {
                            is.close();
                        }
                        baos.close();
                    }
                } else {
                    // se for um campo do formulário então salva seu valor
                    parametros.put(item.getFieldName(), item.getString("utf-8"));
                }
            }
        }
        return parametros;
    }

    /**
     * Grava os bytes do arquivo no disco.
     *
     * @param arquivo
     * @return
     * @throws FileNotFoundException
     * @throws IOException
     */
    public static String gravarArquivo(Arquivo arquivo) throws FileNotFoundException, IOException {
        File arquivoGravado = new File(DIRETORIO_ARQUIVOS, arquivo.getNome());

        try (FileOutputStream fos = new FileOutputStream(arquivoGravado)) {
            fos.write(arquivo.getBytes());
        }

        return arquivoGravado.getName();
    }

    /**
     * Lê o arquivo do disco e transforma em um array de bytes.
     *
     * @param caminho
     * @return
     * @throws FileNotFoundException
     * @throws IOException
     */
    public static Arquivo lerArquivo(String caminho) throws FileNotFoundException, IOException {
        File arquivoGravado = new File(DIRETORIO_ARQUIVOS, caminho);

        try (FileInputStream fis = new FileInputStream(arquivoGravado)) {
            byte[] buffer = new byte[(int) arquivoGravado.length()];
            IOUtils.readFully(fis, buffer);

            Arquivo arquivo = new Arquivo();
            arquivo.setBytes(buffer);
            arquivo.setNome(arquivoGravado.getName());

            return arquivo;
        }
    }
}
