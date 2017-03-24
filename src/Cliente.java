/**
 * Created by Eudes on 23/03/2017.
 */
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

public class Cliente {
    public static void main(String[] args) throws IOException {

        //cria um socket com o google na porta 80
        Socket socket = new Socket("localhost", 8000);
        //verifica se esta conectado
        if (socket.isConnected()) {
            //imprime o endereço de IP do servidor
            System.out.println("Conectado a " + socket.getInetAddress());
        }

        /* Veja que a requisição termina com \r\n que equivale a <CR><LF> para encerar a requisição tem uma linha em branco */
        String requisicao = ""
                + "GET / HTTP/1.1\r\n"
                + "Host: localhost\r\n"
                + "\r\n";
        // OutputStream para enviar a requisição
        OutputStream envioServ = socket.getOutputStream();
        // Temos que mandar a requisição no formato de vetor de bytes
        byte[] b = requisicao.getBytes();
        // Escreve o vetor de bytes no "recurso" de envio
        envioServ.write(b);
        // Marca a finalização da escrita
        envioServ.flush();


        /* Lê dados enviados do servidor*/
        // Cria um scanner a partir do InputStream que vem do servidor
        Scanner sc = new Scanner(socket.getInputStream());
        // Enquanto houver algo para ler
        while (sc.hasNext()) {
            //imprime uma linha da resposta
            System.out.println(sc.nextLine());
        }
    }
}

