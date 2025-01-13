
import entities.Sale;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class App {
    public static void main(String[] args) throws Exception {

        Scanner scanner = new Scanner(System.in);
        System.out.print("Entre o caminho do arquivo: ");
        String path = scanner.nextLine();

        List<Sale> sales = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line = br.readLine();
            while (line != null) {

                String[] fields = line.split(",");

                sales.add(new Sale(
                        Integer.parseInt(fields[0]),
                        Integer.parseInt(fields[1]),
                        fields[2],
                        Integer.parseInt(fields[3]),
                        Double.parseDouble(fields[4])));

                line = br.readLine();
            }

            List<Sale> names = sales.stream()
                    .filter(p -> p.getYear() == 2016)
                    .sorted((s1, s2) -> s2.averagePrice().compareTo(s1.averagePrice()))
                    .limit(5)
                    .collect(Collectors.toList());
            System.out.println();
            names.forEach(System.out::println);

            String usuario = "Logan";

            List<Sale> user = sales.stream()
                    .filter(p -> p.getMonth() == 1 || p.getMonth() == 7)
                    .sorted((s1, s2) -> s2.averagePrice().compareTo(s1.averagePrice()))
                    .collect(Collectors.toList());

            double sum = 0.0;
            for (Sale s : user) {
                if (s.getSeller().equals(usuario)) {
                    sum += s.getTotal();
                }
            }
            System.out.println("\nValor total vendido pelo vendedor " + usuario + " nos meses 1 e 7 = " + sum + "\n");

        } catch (IOException e) {
            System.out.println("Error: " + path + " (O sistema não pode encontrar o arquivo especificado)");
        }

    }
}
