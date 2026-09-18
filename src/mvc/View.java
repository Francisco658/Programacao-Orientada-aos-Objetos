package mvc;

import exceptions.FormulaInvalida;
import exceptions.InputIncorreto;
import src.Artigo.Encomenda.Embalagem;

import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;
import src.Admin;
import src.Artigo.Artigo;
import src.Artigo.Encomenda;
import src.Artigo.Mala.Mala;
import src.Artigo.Mala.MalaPremium;
import src.Artigo.Sapatilha.Sapatilha;
import src.Artigo.Sapatilha.SapatilhaPremium;
import src.Artigo.Tshirt.Tshirt;
import src.Utilizador.*;
import src.Utilizador.Transportador;

import java.awt.*;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;
import java.time.LocalDate;
import java.io.Serializable;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class View implements IView, Serializable {

    // Variáveis de Instância
    private IController controller;

    // Construtor por Omissão
    public View() {
        this.controller = new Controller();
    }

    // Construtor Parametrizado
    public View(IController c) {
        this.controller = c;
    }

    // Construtor por Cópia
    public View(View c) {
        this.controller = c.getController();
    }

    // Getters e Setters
    public IController getController() {
        return this.controller;
    }

    public void setController(IController c) {
        this.controller = c;
    }

    // Clone
    @Override
    public View clone() {
        return new View(this);
    }

    // Equals
    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (o == null || (o.getClass() != this.getClass())) {
            return false;
        }
        View v = (View) o;
        return this.controller.equals(v.getController());
    }

    // -------------------------- Menus Iniciais ----------------------------------------------
    // Menu Inicial
    public void runMenuInicial() throws InputIncorreto {
        showMenuInicial();
        boolean exit = false;
        Scanner scanner = new Scanner(System.in);

        while (!exit) {
            System.out.print("Opção > ");

            try {
                int option = scanner.nextInt();
                scanner.nextLine();

                switch (option) {
                    case 0 -> {
                        exit = true;
                        break;
                    }
                    case 1 -> {
                        runMenuLogin(scanner);
                        break;
                    }
                    case 2 -> {
                        runMenuRegisto();
                        break;
                    }
                    default -> throw new InputIncorreto();
                }
            } catch (InputMismatchException e) {
                //System.out.println("Entrada incorreta. Por favor, insira um valor válido.");
                //scanner.nextLine(); // consume the invalid input
                throw new InputIncorreto();
            }
        }
    }

    // Menu do Login
    public void runMenuLogin(Scanner scanner) {
        System.out.print("Insere o teu Email: ");
        String emailLogin = scanner.nextLine();
        while (!this.controller.isValidEmail(emailLogin)) {
            System.out.println("Email inválido! Tenta novamente.");
            System.out.print("Insere o teu Email: ");
            emailLogin = scanner.nextLine();
        }
        System.out.print("Insere a tua Password: ");
        String passwordLogin = scanner.nextLine();
        while (passwordLogin.isEmpty()) {
            System.out.println("A password não pode ser vazia! Tenta novamente.");
            System.out.print("Insere a tua Password: ");
            passwordLogin = scanner.nextLine();
        }
        if (controller.getCompradorVendedor(emailLogin, passwordLogin) != null) {
            CompradorVendedor compradorVendedor = controller.getCompradorVendedor(emailLogin, passwordLogin);
            LocalDate data = this.controller.getTempoDoSistema();
            this.controller.getAvancarNoTempo(data,compradorVendedor);
            runMenuCompradorVendedor(compradorVendedor);
        } else if (controller.getTransportador(emailLogin, passwordLogin) != null) {
            Transportador transportador = controller.getTransportador(emailLogin, passwordLogin);
            runMenuTransportador(transportador);
        } else if (controller.getAdmin(emailLogin, passwordLogin) != null) {
            Admin admin = controller.getAdmin(emailLogin, passwordLogin);
            runMenuAdmin(admin);
        } else {
            System.out.println("Conta não existe.");
            try {
                runMenuInicial();
            } catch (InputIncorreto e) {
                e.printStackTrace();
            }
        }
    }

    // Menu do Registo
    public void runMenuRegisto() {
        showMenuRegistarUtilizador();
        boolean exit = false;
        Scanner scanner = new Scanner(System.in);

        while (!exit) {
            System.out.print("Opção > ");
            int option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
                case 0 -> {
                    try {
                        controller.gravarEstado("output/state.dat");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    exit = true;
                    System.exit(0);
                    break;
                }
                case 1 -> {
                    runRegisterCompradorVendedor(scanner);
                    break;
                }
                case 2 -> {
                    runRegisterTransportador(scanner);
                    break;
                }
                default -> System.out.println("Opção Inválida!");
            }
        }
    }

    // -----------------------------------------------------------------------------------------

    // -------------------------- Menus de Cada Utilizador -------------------------------------
    // Menu de Registo Comprador/Vendedor
    public void runRegisterCompradorVendedor(Scanner scanner) {
        System.out.println("Insere o teu Email: ");
        String email = scanner.nextLine();
        while (!this.controller.isValidEmail(email) || this.controller.verificaEmail(email) || this.controller.verificaEmailTransportador(email)) {
            if (controller.verificaEmail(email)) {
                System.out.println("Email já em uso! Tenta novamente.");
                try {
                    runMenuInicial();
                } catch (InputIncorreto e) {
                    e.printStackTrace();
                }
                return;
            } else {
                System.out.println("Email inválido! Tenta novamente.");
            }
            System.out.println("Insere o teu Email: ");
            email = scanner.nextLine();
        }

        System.out.println("Insere a tua Password: ");
        String password = scanner.nextLine();
        while (password.length() < 6) {
            System.out.println("A password deve ter pelo menos 6 caracteres! Tenta novamente.");
            System.out.println("Insere a tua Password: ");
            password = scanner.nextLine();
        }

        System.out.println("Insere o teu Nome: ");
        String nome = scanner.nextLine();
        while (nome.isEmpty()) {
            System.out.println("Nome inválido! Tenta novamente.");
            System.out.println("Insere o teu Nome: ");
            nome = scanner.nextLine();
        }

        System.out.println("Insere a tua Morada: ");
        String morada = scanner.nextLine();
        while (morada.isEmpty()) {
            System.out.println("Morada inválida! Tenta novamente.");
            System.out.println("Insere a tua Morada: ");
            morada = scanner.nextLine();
        }

        System.out.println("Insere o teu NIF: ");
        int nif = scanner.nextInt();
        while (String.valueOf(nif).length() != 9) {
            System.out.println("O NIF deve ter 9 dígitos! Tenta novamente.");
            System.out.println("Insere o teu NIF: ");
            nif = scanner.nextInt();
        }

        Encomenda encomenda = new Encomenda();
        List<Encomenda> listaEncomendasFinalizada = new ArrayList<>();
        List<Encomenda> listaEncomendasExpedida = new ArrayList<>();
        double valorTotalCompras = 0.0f;
        double valorTotalComprasTempo = 0.0f;
        List<String> listaDeFaturasComprador = new ArrayList<>();
        List<String> listaDeFaturasVendedor = new ArrayList<>();
        Map<LocalDate,Artigo> artigosVendidosData = new HashMap<>();
        List<Artigo> produtosVendidos = new ArrayList<>();
        List<Artigo> produtosVenda = new ArrayList<>();
        double valorTotalVendas = 0.0f;
        double valorTotalVendasTempo = 0.0f;
        CompradorVendedor compradorVendedor = new CompradorVendedor(password, email, nome, morada, nif,
                encomenda, listaEncomendasFinalizada, listaEncomendasExpedida, valorTotalCompras ,
                valorTotalComprasTempo, listaDeFaturasComprador, listaDeFaturasVendedor, artigosVendidosData,
                produtosVendidos ,produtosVenda, valorTotalVendas, valorTotalVendasTempo);
        this.controller.atualizaCompradorVendedor(compradorVendedor);
        runMenuCompradorVendedor(compradorVendedor);
    }

    // Menu de Registo Transportador
    public void runRegisterTransportador(Scanner scanner) {
        System.out.println("Insere o teu Email: ");
        String email = scanner.nextLine();
        while (!this.controller.isValidEmail(email) || this.controller.verificaEmail(email) || this.controller.verificaEmailTransportador(email)) {
            if (controller.verificaEmail(email)) {
                System.out.println("Email já em uso! Tenta novamente.");
                try {
                    runMenuInicial();
                } catch (InputIncorreto e) {
                    e.printStackTrace();
                }
                return;
            } else {
                System.out.println("Email inválido! Tenta novamente.");
            }
            System.out.println("Insere o teu Email: ");
            email = scanner.nextLine();
        }

        System.out.println("Insere a tua Password: ");
        String password = scanner.nextLine();
        while (password.length() < 6) {
            System.out.println("A password deve ter pelo menos 6 caracteres! Tenta novamente.");
            System.out.println("Insere a tua Password: ");
            password = scanner.nextLine();
        }

        System.out.println("Insere o teu Nome: ");
        String nome = scanner.nextLine();
        while (nome.isEmpty()) {
            System.out.println("Nome inválido! Tenta novamente.");
            System.out.println("Insere o teu Nome: ");
            nome = scanner.nextLine();
        }

        System.out.println("Insere a tua Morada: ");
        String morada = scanner.nextLine();
        while (morada.isEmpty()) {
            System.out.println("Morada inválida! Tenta novamente.");
            System.out.println("Insere a tua Morada: ");
            morada = scanner.nextLine();
        }

        System.out.println("Insere o teu NIF: ");
        int nif = scanner.nextInt();
        while (String.valueOf(nif).length() != 9) {
            System.out.println("O NIF deve ter 9 dígitos! Tenta novamente.");
            System.out.println("Insere o teu NIF: ");
            nif = scanner.nextInt();
        }

        float vbpequena;
        do {
            System.out.println("Insere o Valor Base para Encomendas Pequenas Apenas com um Artigo: ");
            vbpequena = scanner.nextFloat();
        } while (vbpequena == 0.0);

        float vbmedia;
        do {
            System.out.println("Insere o Valor Base para Encomendas Medias Apenas com um Artigo: ");
            vbmedia = scanner.nextFloat();
        } while (vbmedia == 0.0);

        float vbgrande;
        do {
            System.out.println("Insere o Valor Base para Encomendas Grandes Apenas com um Artigo: ");
            vbgrande = scanner.nextFloat();
        } while (vbgrande == 0.0);

        float margemLucro;
        do {
            System.out.println("Insere a Margem de Lucro: ");
            margemLucro = scanner.nextFloat();
        } while (margemLucro == 0.0);

        float imposto;
        do {
            System.out.println("Insere o Imposto: ");
            imposto = scanner.nextFloat();
            scanner.nextLine();
        } while (imposto == 0.0);

        boolean flag = false;
        String input = "";
        System.out.print("É uma transportadora premium? (Y/N): ");
        input = scanner.nextLine();
        while (!input.equalsIgnoreCase("Y") && !input.equalsIgnoreCase("N")) {
            System.out.println("Resposta inválida. Por favor, responda Y para sim, N para nao.");
            System.out.print("É uma transportadora premium? (Y/N): ");
            input = scanner.nextLine();
        }

        if (input.equalsIgnoreCase("Y")) {
            flag = true;
            Transportador transportadoraPremium = new Transportador(password, email, nome, morada, nif,
                    vbpequena, vbmedia, vbgrande, imposto, margemLucro, flag,
                    "(VB * MLT * (1 + I)) * 0.9", new ArrayList<Artigo>(),0.0);

            // Pode ser set uma vez que vai ser sempre um Transportador novo
            controller.adicionaTransportadoraPremium(transportadoraPremium);
        } else if (input.equalsIgnoreCase("N")) {
            flag = false;
        }

        Transportador transportador = new Transportador(password, email, nome, morada, nif,
                vbpequena, vbmedia, vbgrande, imposto, margemLucro, flag,
                "(VB * MLT * (1 + I)) * 0.9", new ArrayList<Artigo>(),0.0);

        controller.adicionaTransportadora(transportador);
        runMenuTransportador(transportador);
    }

    // Menu do Admin
    public void runMenuAdmin(Admin admin) {
        showMenuAdmin(admin);
        boolean exit = false;
        Scanner scanner = new Scanner(System.in);

        while (!exit) {
            System.out.print("Opção > ");
            int option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
                case 0:
                    System.out.println("Sessão Terminada!");
                    try {
                        controller.gravarEstado("output/state.dat");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    exit = true;
                    System.exit(0);
                    break;
                case 1:
                    CompradorVendedor compradorVendedor1 = this.controller.getVendedorMaiorFaturacaoSempre();
                    System.out.println("Vendedor com maior faturação desde sempre : ");
                    System.out.print("Nome " + compradorVendedor1.getNome() + ", Valor de Vendas : "
                            + String.format("%.2f",compradorVendedor1.getValorTotalVendas()) + ".");
                    runMenuAdmin(admin);
                case 2:
                    System.out.print("Insira o Periodo de Tempo em que pretende obter a estatística ");
                    System.out.println("no seguinte formato : YYYY-mm-DD YYYY-mm-DD");
                    String periodo2 = scanner.nextLine();
                    while (!this.controller.isValidData1(periodo2)) {
                        System.out.println("Não inseriu um Periodo de Tempo valido!");
                        System.out.print("Insira o Periodo de Tempo em que pretende obter a estatística ");
                        System.out.println("no seguinte formato : YYYY-mm-DD YYYY-mm-DD");
                        periodo2 = scanner.nextLine();
                    }
                    String[] elementos = periodo2.split(" ", 2);
                    while (!this.controller.isValidData2(elementos[0]) || !this.controller.isValidData2(elementos[1])) {
                            System.out.println("As datas que inseriu não são válidas!");
                            System.out.print("Por favor tente outra vez!");
                            runMenuAdmin(admin);
                    }
                    LocalDate localDate1 = LocalDate.parse(elementos[0]);
                    LocalDate localDate2 = LocalDate.parse(elementos[1]);
                    Set<CompradorVendedor> vendedorMaior =
                            this.controller.getMaioresVendedoresPeriodoTempo(localDate1,localDate2);
                    System.out.print("Vendedor com maior faturacao entre " + elementos[0]);
                    System.out.println(" e " + elementos[1] + ":");
                    int num = 0;
                    for (CompradorVendedor cv : vendedorMaior) {
                        if (num == 0) {
                            System.out.print("Nome " + cv.getNome() + ", Valor de Vendas : "
                                    + String.format("%.2f",cv.getValorTotalVendasTempo()) + ".");
                        }
                        num++;
                    }
                    runMenuAdmin(admin);
                    break;
                case 3:
                    Transportador transportador = this.controller.getTransportadorMaiorFaturacaoSempre();
                    System.out.println("Transportador com maior faturação desde sempre : ");
                    System.out.print("Nome : " + transportador.getNome() + ", Faturação : "
                            + String.format("%.2f",transportador.getPrecoTotalEntregas()) + ".");
                    runMenuAdmin(admin);
                    break;
                case 4:
                    List<CompradorVendedor> compradoresVendedores =
                            this.controller.getListaCompradoresVendedores();
                    System.out.println("Lista de Vendedores : ");
                    for (CompradorVendedor cv : compradoresVendedores) {
                        System.out.println("Nome - " + cv.getNome() );
                    }
                    System.out.print("Insira o nome do Vendedor ao qual pretende aceder a lista " +
                            "de artigos vendidos: ");
                    String nome = scanner.nextLine();
                    boolean flag = false;
                    while (!flag) {
                        for (CompradorVendedor cv : compradoresVendedores) {
                            if (cv.getNome().equals(nome)) {
                                flag = true;
                                break;
                            }
                        }
                        if (!flag) {
                            System.out.println("Por favor insira um nome de um Vendedor da Lista disponível!");
                            nome = scanner.nextLine();
                        }
                    }
                    List<Artigo> listaDeArtigos = this.controller.getListaArtigosVendidos(nome);
                    System.out.println("Lista de Artigos Vendidos por " + nome + " :");
                    int c4 = 1;
                    for (Artigo a : listaDeArtigos) {
                        System.out.println(c4 + " - " + a.toString());
                        c4++;
                    }
                    runMenuAdmin(admin);
                    break;
                case 5:
                    System.out.println("Lista dos maiores Compradores desde sempre : ");
                    Set<CompradorVendedor> compradorOrdem = this.controller.getMaioresCompradoresSempre();
                    int i5 = 1;
                    for (CompradorVendedor cv : compradorOrdem) {
                        if (cv.getValorTotalCompras() > 0) {
                            System.out.println(i5 + " - " + "Nome " + cv.getNome() + ", Valor de Compras : "
                                    + String.format("%.2f",cv.getValorTotalCompras()) + ".");
                            i5++;
                        }
                    }
                    runMenuAdmin(admin);
                    break;
                case 6:
                    System.out.print("Insira o Periodo de Tempo em que pretende obter a estatística ");
                    System.out.println("no seguinte formato : YYYY-mm-DD YYYY-mm-DD");
                    String periodo6 = scanner.nextLine();
                    while (!this.controller.isValidData1(periodo6)) {
                        System.out.println("Não inseriu um Periodo de Tempo valido!");
                        System.out.print("Insira o Periodo de Tempo em que pretende obter a estatística ");
                        System.out.println("no seguinte formato : YYYY-mm-DD YYYY-mm-DD");
                        periodo6 = scanner.nextLine();
                    }
                    String[] elementos6 = periodo6.split(" ", 2);
                    while (!this.controller.isValidData2(elementos6[0]) || !this.controller.isValidData2(elementos6[1])) {
                        System.out.println("As datas que inseriu não são válidas!");
                        System.out.print("Por favor tente outra vez!");
                        runMenuAdmin(admin);
                    }
                    LocalDate localDate3 = LocalDate.parse(elementos6[0]);
                    LocalDate localDate4 = LocalDate.parse(elementos6[1]);
                    System.out.print("Lista de Compradores com maior faturacao entre " + elementos6[0]);
                    System.out.println(" e " + elementos6[1] + ":");
                    Set<CompradorVendedor> compradorOrdemTempo =
                            this.controller.getMaioresCompradoresPeriodoTempo(localDate3,localDate4);
                    int i6 = 1;
                    for (CompradorVendedor cv : compradorOrdemTempo) {
                        if (cv.getValorTotalComprasTempo() > 0) {
                            System.out.println(i6 + " - " + "Nome " + cv.getNome() + ", Valor de Compras : "
                                    + String.format("%.2f",cv.getValorTotalComprasTempo()) + ".");
                            i6++;
                        }
                    }
                    runMenuAdmin(admin);
                    break;
                case 7:
                    System.out.println("Lista dos maiores Vendedores desde sempre : ");
                    Set<CompradorVendedor> vendedorOrdem = this.controller.getMaioresVendedoresSempre();
                    int i7 = 1;
                    for (CompradorVendedor cv : vendedorOrdem) {
                        if (cv.getValorTotalVendas() > 0) {
                            System.out.println(i7 + " - " + "Nome " + cv.getNome() + ", Valor de Vendas : "
                                    + String.format("%.2f",cv.getValorTotalVendas()) + ".");
                            i7++;
                        }
                    }
                    runMenuAdmin(admin);
                    break;
                case 8:
                    System.out.print("Insira o Periodo de Tempo em que pretende obter a estatística ");
                    System.out.println("no seguinte formato : YYYY-mm-DD YYYY-mm-DD");
                    String periodo8 = scanner.nextLine();
                    while (!this.controller.isValidData1(periodo8)) {
                        System.out.println("Não inseriu um Periodo de Tempo valido!");
                        System.out.print("Insira o Periodo de Tempo em que pretende obter a estatística ");
                        System.out.println("no seguinte formato : YYYY-mm-DD YYYY-mm-DD");
                        periodo8 = scanner.nextLine();
                    }
                    String[] elementos8 = periodo8.split(" ", 2);
                    while (!this.controller.isValidData2(elementos8[0]) || !this.controller.isValidData2(elementos8[1])) {
                        System.out.println("As datas que inseriu não são válidas!");
                        System.out.print("Por favor tente outra vez!");
                        runMenuAdmin(admin);
                    }
                    LocalDate localDate5 = LocalDate.parse(elementos8[0]);
                    LocalDate localDate6 = LocalDate.parse(elementos8[1]);
                    System.out.print("Lista de Vendedores com maior faturacao entre " + elementos8[0]);
                    System.out.println(" e " + elementos8[1] + ":");
                    Set<CompradorVendedor> vendedorOrdemTempo =
                            this.controller.getMaioresVendedoresPeriodoTempo(localDate5,localDate6);
                    int i8 = 1;
                    for (CompradorVendedor cv : vendedorOrdemTempo) {
                        if (cv.getValorTotalVendasTempo() > 0) {
                            System.out.println(i8 + " - " + "Nome " + cv.getNome() + ", Valor de Vendas : "
                                    + String.format("%.2f",cv.getValorTotalVendasTempo()) + ".");
                            i8++;
                        }
                    }
                    runMenuAdmin(admin);
                    break;
                case 9:
                    System.out.println("A Vintage tem um lucro de " + this.controller.getLucroVintage() + ".\n");
                    runMenuAdmin(admin);
                    break;
                default:
                    System.out.println("Opção Inválida!");
                    break;
            }
        }
    }

    // ------------------------------------------------------------------------------------------

    // -------------------------- Menus Transportador -------------------------------------------
    // Menu do Transportador
    public void runMenuTransportador(Transportador transportador) {
        showMenuTransportador(transportador);
        boolean exit = false;
        Scanner scanner = new Scanner(System.in);

        while (!exit) {
            System.out.print("Opção > ");
            int option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
                case 0 -> {
                    System.out.println("Sessão Terminada!");
                    try {
                        this.controller.gravarEstado("output/state.dat");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    exit = true;
                    System.exit(0);
                }
                case 1 -> {
                    List<Artigo> artigosAEntregar = this.controller.getListaArtigosAEntregar(transportador);
                    System.out.println("Lista de Artigos a Entregar : ");
                    for (Artigo a : artigosAEntregar) {
                        System.out.println("Artigo : " + a.getTitulo() + ", Comprador : " + a.getComprador() +
                                ", Data de Entrega Limite : " + a.getDataEntrega());
                    }
                    runMenuTransportador(transportador);
                }
                case 2 -> runMenuAlterarFormula(transportador);
                case 3 -> {
                    try {
                        runMenuNovaFormula(transportador);
                    } catch (FormulaInvalida e) {
                        System.out.println("Fórmula Inválida");
                        runMenuTransportador(transportador);
                    }
                }
                default -> System.out.println("Opção Inválida.");
            }
        }
    }

    // Menu para Alterar Apenas os Valores
    public void runMenuAlterarFormula(Transportador transportador) {
        showMenuAlterarFormula(transportador);
        boolean exit = false;
        Scanner scanner = new Scanner(System.in);
        boolean ocorreu = false;

        while (!exit) {
            System.out.print("Opção > ");
            int option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
                case 0 -> {
                    if(ocorreu) {
                        if(transportador.isTransportePremium()) {
                            System.out.println("Valores do Transportador Premium" + transportador.getNome() + " atualizados!");
                            this.controller.adicionaTransportadoraPremium(transportador);
                        } else {
                            System.out.println("Valores do Transportador " + transportador.getNome() + " atualizados!");
                            this.controller.atualizaTransportador(transportador);
                        }
                        runMenuTransportador(transportador);
                        exit = true;
                        break;
                    }
                    else {
                        if(transportador.isTransportePremium()) {
                            System.out.println("Valores do Transportador Premium" + transportador.getNome() + " foram mantidos!");
                        } else {
                            System.out.println("Valores do Transportador " + transportador.getNome() + " foram mantidos!");
                        }
                        runMenuTransportador(transportador);
                        exit = true;
                        break;
                    }
                }
                case 1 -> {
                    System.out.println("Insere o novo valor base de expedição para encomendas pequenas (1 artigo) (Atual: " + transportador.getValorBaseExpedicaoPequena() + ") :");
                    float valorBaseExpedicaoPequena = scanner.nextFloat();
                    transportador.setValorBaseExpedicaoPequena(valorBaseExpedicaoPequena);
                    System.out.println("Valor base de expedição para encomendas pequenas atualizado com sucesso para " + transportador.getValorBaseExpedicaoPequena());
                    ocorreu = true;
                    break;
                }
                case 2 -> {
                    System.out.println("Insere o novo valor base de expedição para encomendas médias (2 a 5 artigos) (Atual: " + transportador.getValorBaseExpedicaoMedia() + ") :");
                    float valorBaseExpedicaoMedia = scanner.nextFloat();
                    transportador.setValorBaseExpedicaoMedia(valorBaseExpedicaoMedia);
                    System.out.println("Valor base de expedição para encomendas médias atualizado com sucesso para " + transportador.getValorBaseExpedicaoMedia());
                    ocorreu = true;
                    break;
                }
                case 3 -> {
                    System.out.println("Insere o novo valor base de expedição para encomendas grandes (mais que 5 artigos) (Atual: " + transportador.getValorBaseExpedicaoGrande() + ") :");
                    float valorBaseExpedicaoGrande = scanner.nextFloat();
                    transportador.setValorBaseExpedicaoGrande(valorBaseExpedicaoGrande);
                    System.out.println("Valor base de expedição para encomendas grandes atualizado com sucesso para " + transportador.getValorBaseExpedicaoGrande());
                    ocorreu = true;
                    break;
                }
                case 4 -> {
                    System.out.println("Insere o novo fator multiplicativo de impostos (Atual: " + transportador.getFatorMultiplicativoImpostos() + ") :");
                    float fatorMultiplicativoImpostos = scanner.nextFloat();
                    transportador.setFatorMultiplicativoImpostos(fatorMultiplicativoImpostos);
                    System.out.println("Fator multiplicativo de impostos atualizado com sucesso para " + transportador.getFatorMultiplicativoImpostos());
                    ocorreu = true;
                    break;
                }
                case 5 -> {
                    System.out.println("Insere a nova margem de lucro (Atual: " + transportador.getMargemLucro() + ") :");
                    float margemLucro = scanner.nextFloat();
                    transportador.setMargemLucro(margemLucro);
                    System.out.println("Margem de lucro atualizada com sucesso para " + transportador.getMargemLucro());
                    ocorreu = true;
                    break;
                }
                default -> System.out.println("Opção Inválida.");
            }
        }
    }

    // Menu para Alterar a Formula Toda
    public void runMenuNovaFormula(Transportador transportador) throws FormulaInvalida {
        Scanner scanner = new Scanner(System.in);

        boolean validFormula = false;
        String formula;
        do {
            System.out.println("Digite sua fórmula usando VB, MLT e I, números e operações aritméticas básicas:");
            formula = scanner.nextLine();

            if (formula.matches("^[0-9+\\-*/().\\sVBMLTI]*$")) {
                validFormula = true;
            } else {
                System.out.println("Fórmula inválida. Por favor, use apenas VB, MLT, I, números e operações aritméticas básicas.");
                throw new FormulaInvalida("Fórmula inválida. Por favor, use apenas VB, MLT, I, números e operações aritméticas básicas.");
            }
        } while (!validFormula);

        transportador.setFormula(formula);

        System.out.println("Digite o valor de VB Grande:");
        float vbGrande = scanner.nextFloat();
        transportador.setValorBaseExpedicaoGrande(vbGrande);

        System.out.println("Digite o valor de VB Média:");
        float vbMedia = scanner.nextFloat();
        transportador.setValorBaseExpedicaoMedia(vbMedia);

        System.out.println("Digite o valor de VB Pequena:");
        float vbPequena = scanner.nextFloat();
        transportador.setValorBaseExpedicaoPequena(vbPequena);

        System.out.println("Digite o valor de MLT:");
        float mlt = scanner.nextFloat();
        transportador.setMargemLucro(mlt);

        System.out.println("Digite o valor de I:");
        float imposto = scanner.nextFloat();
        transportador.setFatorMultiplicativoImpostos(imposto);

        Expression expression;
        if (transportador.getArtigosAEntregar().size() == 1) {
            expression = new ExpressionBuilder(formula)
                    .variables("VB", "MLT", "I")
                    .build()
                    .setVariable("VB", vbPequena)
                    .setVariable("MLT", mlt)
                    .setVariable("I", imposto);

            try {
                expression.evaluate();
            } catch (IllegalArgumentException e) {
                System.out.println("Ocorreu um erro ao avaliar a fórmula: " + e.getMessage());
            }
        } else if (transportador.getArtigosAEntregar().size() >= 2 && transportador.getArtigosAEntregar().size() < 5) {
            expression = new ExpressionBuilder(formula)
                    .variables("VB", "MLT", "I")
                    .build()
                    .setVariable("VB", vbMedia)
                    .setVariable("MLT", mlt)
                    .setVariable("I", imposto);

            try {
                expression.evaluate();
            } catch (IllegalArgumentException e) {
                System.out.println("Ocorreu um erro ao avaliar a fórmula: " + e.getMessage());
            }
        } else if (transportador.getArtigosAEntregar().size() >= 5) {
            expression = new ExpressionBuilder(formula)
                    .variables("VB", "MLT", "I")
                    .build()
                    .setVariable("VB", vbGrande)
                    .setVariable("MLT", mlt)
                    .setVariable("I", imposto);

            try {
                expression.evaluate();
            } catch (IllegalArgumentException e) {
                System.out.println("Ocorreu um erro ao avaliar a fórmula: " + e.getMessage());
            }
        }

        if(transportador.isTransportePremium()) {
            System.out.println("A fórmula do Transportador Premium" + transportador.getNome() + " foi atualizada!");
            this.controller.atualizaTransportadorPremium(transportador);
        } else {
            System.out.println("A fórmula do Transportador " + transportador.getNome() + " foi atualizada!");
            this.controller.atualizaTransportador(transportador);
        }
        runMenuTransportador(transportador);
    }

    // ------------------------------------------------------------------------------------------

    // -------------------------- Menus Comprador Vendedor---------------------------------------
    // Menu do CompradorVendedor
    public void runMenuCompradorVendedor(CompradorVendedor compradorVendedor) {
        showMenuCompradorVendedor(compradorVendedor);
        boolean exit = false;
        Scanner scanner = new Scanner(System.in);

        while (!exit) {
            System.out.print("Opção > ");
            int option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
                case 0 -> {
                    System.out.println("Sessão Terminada!");
                    try {
                        controller.gravarEstado("output/state.dat");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    exit = true;
                    System.exit(0);
                }
                case 1 -> {
                    List<Artigo> catalogoDeArtigos = this.controller.getArtigosCatalogo();
                    if (catalogoDeArtigos.size() == 0) {
                        System.out.println("O Catálogo de Artigos está vazio.");
                        break;
                    } else {
                        int i = 1;
                        System.out.println("Catálogo de Artigos:");
                        for (Artigo a : catalogoDeArtigos) {
                            Artigo artigo = a.clone();
                            System.out.print(i + " - ");
                            System.out.println(artigo.toString());
                            i++;
                        }
                        runMenuAdicionarArtigo(compradorVendedor);
                    }
                    runMenuCompradorVendedor(compradorVendedor);
                }
                case 2 -> {
                    List<Artigo> listaDeArtigos = this.controller.getArtigosEncomenda(compradorVendedor);
                    if (listaDeArtigos.isEmpty()) {
                        System.out.println("O seu Carrinho de Compras Vazio!");
                        break;
                    } else {
                        int i = 1;
                        System.out.println("Carrinho de Compras: ");
                        for (Artigo a : listaDeArtigos) {
                            System.out.print(i + " - ");
                            System.out.println(a.toString());
                            i++;
                        }
                        runMenuConsultarCarrinhoCompras(compradorVendedor);
                    }
                    runMenuCompradorVendedor(compradorVendedor);
                }
                case 3 -> {
                    List<Encomenda> encomendasExpedidas = this.controller.getEncomendasExpedidas(compradorVendedor);
                    if (encomendasExpedidas.isEmpty()) {
                        System.out.println("Não tem quaisquer encomendas a serem exportadas!");
                    } else {
                        System.out.println("Encomendas a serem exportadas: ");
                        for (Encomenda e : encomendasExpedidas) {
                            System.out.println(e.toString());
                        }
                    }
                    runMenuCompradorVendedor(compradorVendedor);
                }
                case 4 -> {
                    List<Encomenda> listaEncomendas = this.controller.getEncomendasFinalizadas(compradorVendedor);
                    if (listaEncomendas.isEmpty()) {
                        System.out.println("Não tem encomendas Finalizadas.");
                        runMenuCompradorVendedor(compradorVendedor);
                    } else {
                        System.out.println("Qual das suas Encomendas pretende devolver ?");
                        int size = listaEncomendas.size();
                        int i3 = 1;
                        for (Encomenda e : listaEncomendas) {
                            System.out.print(i3 + " - ");
                            System.out.println(e.toString());
                            i3++;
                        }
                        System.out.print("Opção (0-" + size + ") > ");
                        int option3 = scanner.nextInt();
                        scanner.nextLine();
                        while (option3 < 0 || option3 > size) {
                            System.out.println("Opção inválida. Por favor, insira um valor entre 0 e " + size + " :");
                            option3 = scanner.nextInt();
                            scanner.nextLine();
                        }
                        if (option3 == 0) {
                            exit = true;
                        } else {
                            Encomenda ed = listaEncomendas.get(option3 - 1);
                            System.out.println("Tem a certeza que pretende devolver esta encomenda?\n" + ed.toString());
                            System.out.print("Opção (Y/N) > ");
                            String input = scanner.nextLine().toUpperCase();
                            if (input.equals("Y")) {
                                this.controller.getDevolverEncomenda(compradorVendedor,ed);
                                System.out.println("Encomenda Devolvida!");
                            } else if (input.equals("N")) {
                                System.out.println("Nenhuma encomenda foi devolvida!");
                                runMenuCompradorVendedor(compradorVendedor);
                            } else {
                                System.out.println("Resposta inválida! Tenta novamente.");
                            }
                        }
                        runMenuCompradorVendedor(compradorVendedor);
                    }
                }
                case 5 -> {
                    List<Artigo> produtosVenda = this.controller.getProdutosVenda(compradorVendedor);
                    if (produtosVenda.isEmpty()) {
                        System.out.println("Não tem produtos à venda!");
                        break;
                    } else {
                        int i4 = 1;
                        System.out.println("Lista de Produtos à venda: ");
                        for (Artigo a : produtosVenda) {
                            System.out.print(i4 + " - ");
                            System.out.println(a.toString());
                            i4++;
                        }
                    }
                    runMenuCompradorVendedor(compradorVendedor);
                }
                case 6 -> runMenuSelecionarArtigo(compradorVendedor);
                case 7 -> {
                    List<Artigo> catalogoDeArtigos4 = this.controller.getProdutosVenda(compradorVendedor);
                    if (catalogoDeArtigos4.size() == 0) {
                        System.out.println("O Catálogo de Artigos está vazio.");
                        break;
                    } else {
                        int j = 1;
                        System.out.println("Lista de Produtos à venda:");
                        for (Artigo a : catalogoDeArtigos4) {
                            Artigo artigo = a.clone();
                            System.out.print(j + " - ");
                            System.out.println(artigo.toString());
                            j++;
                        }
                        runMenuRemoveArtigoCompradorVendedor(compradorVendedor);
                    }
                    runMenuCompradorVendedor(compradorVendedor);
                }
                case 8 -> {
                    List<String> listaDeFaturasComprador = this.controller.getListaFaturasComprador(compradorVendedor);
                    if (listaDeFaturasComprador.isEmpty()) {
                        System.out.println("Lista de Faturas das Encomendas do Comprador "
                                + compradorVendedor.getNome() + " está vazia!\n");
                    } else {
                        System.out.println("Lista de Faturas das Encomendas do Comprador "
                                + compradorVendedor.getNome() + ":");
                        for (String s : listaDeFaturasComprador) {
                            System.out.println(s);
                        }
                    }
                    List<String> listaDeFaturasVendedor = this.controller.getListaFaturasVendedor(compradorVendedor);
                    if (listaDeFaturasVendedor.isEmpty()) {
                        System.out.println("Lista de Faturas dos Artigos do Vendedor "
                                + compradorVendedor.getNome() + " está vazia!");
                    } else {
                        System.out.println("Lista de Faturas dos Artigos do Vendedor "
                                + compradorVendedor.getNome() + ":");
                        for (String s : listaDeFaturasVendedor) {
                            System.out.println(s);
                        }
                    }
                    runMenuCompradorVendedor(compradorVendedor);
                    break;
                }
                case 9 -> {
                    LocalDate tempoSistema = this.controller.getTempoDoSistema();
                    System.out.println("Data atual do Sistema: " + tempoSistema);
                    System.out.print("Para que data (YYYY-mm-DD) pretende avançar: ");
                    String periodo = scanner.nextLine();
                    while (!this.controller.isValidData2(periodo)) {
                        System.out.println("A data que inseriu não é válida!");
                        System.out.print("Por favor tente outra vez: ");
                        periodo = scanner.nextLine();
                    }
                    LocalDate tempoAvancar = LocalDate.parse(periodo);
                    while (!tempoSistema.isBefore(tempoAvancar)) {
                        System.out.println("A data que inseriu é anterior a data atual do sistema!");
                        System.out.print("Por favor tente outra vez: ");
                        periodo = scanner.nextLine();
                        while (!this.controller.isValidData2(periodo)) {
                            System.out.println("A data que inseriu não é válida!");
                            System.out.print("Por favor tente outra vez: ");
                            periodo = scanner.nextLine();
                        }
                        tempoAvancar = LocalDate.parse(periodo);
                    }

                    this.controller.getAvancarNoTempo(tempoAvancar,compradorVendedor);
                    System.out.println("A data do sistema é agora " + periodo + "!");

                    runMenuCompradorVendedor(compradorVendedor);
                }
                default -> System.out.println("Opção Inválida!!");
            }
        }
    }

    // Opção 1 Menu CompradorVendedor
    public void runMenuAdicionarArtigo(CompradorVendedor compradorVendedor) {
        int size = controller.getListaDeArtigos().size();
        if (size == 0) {
            System.out.println("Sem Artigos publicados adiciona primeiro!");
            return;
        }

        showAdicionarArtigo(compradorVendedor);
        boolean exit = false;
        Scanner scanner = new Scanner(System.in);

        while (!exit) {
            System.out.print("Opção (1-" + size + ") > ");
            int option = scanner.nextInt();
            scanner.nextLine();

            if (option < 0 || option > size) {
                System.out.println("Opção inválida. Por favor, insira um valor entre 1 e " + size + " ou 0 para Voltar atrás.");
                runMenuAdicionarArtigo(compradorVendedor);
                return;
            }

            switch (option) {
                case 0 -> {
                    exit = true;
                    break;
                }

                default -> {
                    List<Artigo> CatalogoArtigos= controller.getListaDeArtigos();
                    Artigo artigo = CatalogoArtigos.get(option - 1);
                    List<Artigo> artigosCV = controller.getProdutosVenda(compradorVendedor);

                    while (artigosCV.contains(artigo)) {
                        System.out.println("Esse Artigo já lhe pertence. Por favor, selecione outro artigo para comprar ou volte para o menu anterior.");

                        System.out.print("Opção (1-" + size + ") > ");
                        option = scanner.nextInt();
                        scanner.nextLine();

                        if (option < 0 || option > size) {
                            System.out.println("Opção inválida. Por favor, insira um valor entre 1 e " + size + " ou 0 para Voltar atrás.");
                            runMenuAdicionarArtigo(compradorVendedor);
                            return;
                        }

                        artigo = CatalogoArtigos.get(option - 1);
                        artigosCV = controller.getProdutosVenda(compradorVendedor);
                    }

                    System.out.println(artigo);
                    Encomenda carrinhoDeCompras = compradorVendedor.getCarrinhoDeCompras();
                    List<Artigo> listaArtigos = carrinhoDeCompras.getArtigos();

                    int stockArtigo = artigo.getStock();
                    if(stockArtigo == 1) {
                        controller.removeArtigo(artigo);

                        boolean itemExists = false;
                        for (Artigo a : listaArtigos) {
                            if (controller.igualdade(a, artigo)) {
                                a.setStock(a.getStock() + 1);
                                itemExists = true;
                                break;
                            }
                        }

                        if (!itemExists) {
                            Artigo artigoComNovoStock = artigo.clone();
                            artigoComNovoStock.setStock(1);
                            listaArtigos.add(artigoComNovoStock);
                        }

                        carrinhoDeCompras.setArtigos(listaArtigos);
                        compradorVendedor.setCarrinhoDeCompras(carrinhoDeCompras);
                        controller.atualizaCompradorVendedor(compradorVendedor);
                    } else {
                        System.out.print("Quantos Itens deseja adicionar ao Carrinho de Compras? ");
                        int numItems = scanner.nextInt();
                        scanner.nextLine();

                        if (numItems <= 0 || numItems > stockArtigo) {
                            System.out.println("Stock Indisponível.");
                            runMenuAdicionarArtigo(compradorVendedor);
                            return;
                        } else if (numItems == stockArtigo) {
                            controller.removeArtigo(artigo);

                            boolean itemExists = false;
                            for (Artigo a : listaArtigos) {
                                if (controller.igualdade(a, artigo)) {
                                    a.setStock(a.getStock() + numItems);
                                    itemExists = true;
                                    break;
                                }
                            }

                            if (!itemExists) {
                                Artigo artigoComNovoStock = artigo.clone();
                                artigoComNovoStock.setStock(numItems);
                                listaArtigos.add(artigoComNovoStock);
                            }

                            carrinhoDeCompras.setArtigos(listaArtigos);
                            compradorVendedor.setCarrinhoDeCompras(carrinhoDeCompras);
                            controller.atualizaCompradorVendedor(compradorVendedor);
                        } else {
                            stockArtigo -= numItems;
                            controller.alteraStockArtigo(artigo, stockArtigo);

                            boolean itemExists = false;
                            for (Artigo a : listaArtigos) {
                                if (controller.igualdade(a, artigo)) {
                                    a.setStock(a.getStock() + numItems);
                                    itemExists = true;
                                    break;
                                }
                            }

                            if (!itemExists) {
                                Artigo artigoComNovoStock = artigo.clone();
                                artigoComNovoStock.setStock(numItems);
                                listaArtigos.add(artigoComNovoStock);
                            }

                            carrinhoDeCompras.setArtigos(listaArtigos);
                            compradorVendedor.setCarrinhoDeCompras(carrinhoDeCompras);
                            controller.atualizaCompradorVendedor(compradorVendedor);
                        }
                    }
                    System.out.println("Artigo adicionado ao Carrinho com Sucesso!");
                    runMenuCompradorVendedor(compradorVendedor);
                    break;
                }
            }
        }
    }

    // Opção 2 Menu Comprador Vendedor
    public void runMenuConsultarCarrinhoCompras(CompradorVendedor compradorVendedor) {
        System.out.print("\n");
        showConsultarCarrinhoCompras(compradorVendedor);
        boolean exit = false;
        Scanner scanner = new Scanner(System.in);

        while (!exit) {
            System.out.print("Opção > ");
            int option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
                case 0 -> {
                    exit = true;
                    runMenuCompradorVendedor(compradorVendedor);
                    break;
                }
                case 1 -> {
                    Encomenda encomenda = compradorVendedor.getCarrinhoDeCompras();
                    double custoFinal = encomenda.calcularPrecoFinal();
                    double custoExpedicao = encomenda.calcularCustosExpedicao();
                    Embalagem embalagem = encomenda.getEmbalagem();
                    embalagem = encomenda.atualizaEmbalagem();
                    encomenda.setPrecoFinal(custoFinal);
                    encomenda.setCustosExpedicao(custoExpedicao);
                    encomenda.setEmbalagem(embalagem);
                    compradorVendedor.setCarrinhoDeCompras(encomenda);
                    Encomenda encomendaAtualizada = compradorVendedor.getCarrinhoDeCompras();
                    List<Artigo> listaArtigos = encomendaAtualizada.getArtigos();
                    LocalDate dataEntrega = null;
                    int maior = 0;
                    for (Artigo a : listaArtigos) {
                        int random = (int) (Math.random() * (11 - 2 + 1) + 2);
                        LocalDate data = this.controller.getTempoDoSistema().plusDays(random);
                        a.setDataEntrega(data);
                        if (maior < random) {
                            dataEntrega = data;
                        }
                    }
                    encomendaAtualizada.setDataEntrega(dataEntrega);
                    encomendaAtualizada.setDataExpedicao(this.controller.getTempoDoSistema());
                    this.controller.getAtualizaEncomenda(compradorVendedor,encomenda);
                    System.out.println("Tem a certeza de que pretende finalizar a seguinte Encomenda ?");
                    System.out.println(encomenda);

                    String input;
                    boolean validInput = false;
                    while (!validInput) {
                        System.out.print("Opção (Y/N) > ");
                        input = scanner.nextLine().toUpperCase();
                        if (input.equals("Y")) {
                            validInput = true;
                            this.controller.getFinalizaEncomenda(compradorVendedor);
                            System.out.println("Acabou de efetuar a seguinte Encomenda.");
                            runMenuCompradorVendedor(compradorVendedor);
                            break;
                        } else if (input.equals("N")) {
                            validInput = true;
                            System.out.println("Encomenda não finalizada.\n");
                            runMenuCompradorVendedor(compradorVendedor);
                            break;
                        } else {
                            System.out.println("Opção inválida. Por favor, escolha Y ou N.");
                        }
                    }
                    break;
                }
                case 2 -> {
                    Encomenda encomenda = compradorVendedor.getCarrinhoDeCompras();
                    List<Artigo> carrinhoCompras = encomenda.getArtigos();

                    if (carrinhoCompras.size() == 0 ) {
                        System.out.println("O Carrinho de Compras está vazio.");
                        break;
                    } else {
                        int i = 1;
                        System.out.println("Carrinho de Compras:");
                        for (Artigo a : carrinhoCompras) {
                            Artigo artigo = a.clone();
                            System.out.print(i + " - ");
                            System.out.println(artigo.toString());
                            i++;
                        }
                        System.out.print("\n");
                        runMenuRemoverCarrinho(compradorVendedor);
                    }
                    runMenuCompradorVendedor(compradorVendedor);
                    break;
                }
                default -> {
                    System.out.println("Opção Inválida.");
                    break;
                }
            }
        }
    }

    // Menu remover artigo / numero de itens do mesmo artigo do Carrinho de Compras
    public void runMenuRemoverCarrinho(CompradorVendedor compradorVendedor) {
        Encomenda encomenda = compradorVendedor.getCarrinhoDeCompras();
        List<Artigo> carrinhoCompras = encomenda.getArtigos();

        int size = carrinhoCompras.size();
        if (size == 0) {
            System.out.println("Sem Artigos publicados, adiciona primeiro!");
            return;
        }

        showMenuRemoveArtigo(compradorVendedor);
        boolean exit = false;
        Scanner scanner = new Scanner(System.in);

        while (!exit) {
            System.out.print("Opção (0-" + size + ") > ");
            int option = scanner.nextInt();
            scanner.nextLine();

            if (option < 0 || option > size) {
                System.out.println("Opção inválida. Por favor, insira um valor entre 0 e " + size);
            } else if (option == 0) {
                exit = true;
            } else {
                Artigo artigo = controller.getArtigoCompradorVendedorCarrinhodeCompras(compradorVendedor, option);
                System.out.println(artigo);

                int currentStock = artigo.getStock();
                if (currentStock <= 0) {
                    System.out.println("Não há stock disponível para este artigo!");
                } else if (currentStock == 1) {
                    controller.removeArtigoCarrinhoCompras(compradorVendedor, artigo);

                    controller.adicionarStockArtigo(artigo, 1);

                    controller.atualizaCompradorVendedor(compradorVendedor);

                    System.out.println("Foi removido o Artigo " + artigo.getTitulo() + ".");
                    break;
                } else {
                    boolean validInput = false;
                    int quantidadeRemover = 0;

                    while (!validInput) {
                        System.out.print("Quantidade a remover (Máx: " + currentStock + "): ");
                        try {
                            quantidadeRemover = scanner.nextInt();
                            scanner.nextLine();

                            if (quantidadeRemover <= 0) {
                                System.out.println("A quantidade deve ser um número positivo!");
                            } else if (quantidadeRemover > currentStock) {
                                System.out.println("A quantidade não pode ser superior ao stock atual (" + currentStock + ")!");
                            } else {
                                validInput = true;
                            }
                        } catch (InputMismatchException e) {
                            System.out.println("Input inválido. Insere um número inteiro válido.");
                            scanner.nextLine();
                        }
                    }

                    if (quantidadeRemover == currentStock) {
                        controller.removeArtigoCarrinhoCompras(compradorVendedor, artigo);

                        controller.adicionarStockArtigo(artigo, quantidadeRemover);

                        controller.atualizaCompradorVendedor(compradorVendedor);

                        System.out.println("Foram removidos " + quantidadeRemover + " unidades do Artigo " + artigo.getTitulo() + ".");
                    } else {
                        int stockManter = currentStock - quantidadeRemover;
                        controller.adicionarStockArtigo(artigo, quantidadeRemover);

                        controller.removeStockArtigoCarrinhoCompras(compradorVendedor, option, stockManter);

                        controller.atualizaCompradorVendedor(compradorVendedor);

                        if(quantidadeRemover == 1) {
                            System.out.println("Foi removido " + quantidadeRemover + " unidade do Artigo " + artigo.getTitulo() + ".");
                        }
                        else {
                            System.out.println("Foram removidos " + quantidadeRemover + " unidades do Artigo " + artigo.getTitulo() + ".");
                        }
                    }
                    break;
                }
            }
        }
        runMenuCompradorVendedor(compradorVendedor);
    }

    // Opção 4 Menu Comprador/Vendedor
    public void runMenuSelecionarArtigo(CompradorVendedor compradorVendedor) {
        showMenuSelecionarArtigo(compradorVendedor);
        boolean exit = false;
        Scanner scanner = new Scanner(System.in);

        while (!exit) {
            System.out.print("Opção > ");
            int option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
                case 0 -> {
                    runMenuCompradorVendedor(compradorVendedor);
                    exit = true;
                }
                case 1 -> {
                    runMenuAdicionarStock(compradorVendedor);
                    runMenuCompradorVendedor(compradorVendedor);
                }
                case 2 -> runMenuTipoArtigo(compradorVendedor);
                default -> System.out.println("Opção inválida!");
            }
        }
    }

    // Menu Adicionar Stock
    public void runMenuAdicionarStock(CompradorVendedor compradorVendedor) {
        List<Artigo> catalogoDeArtigos = compradorVendedor.getProdutosVenda();
        if (catalogoDeArtigos.isEmpty()) {
            System.out.println("A lista de produtos à venda está vazia.");
            return;
        }
        else {
            int k = 1;
            System.out.println("Catálogo de Artigos " + compradorVendedor.getNome() +" :");
            for (Artigo a : catalogoDeArtigos) {
                Artigo artigo = a.clone();
                System.out.print(k + " - ");
                System.out.println(artigo.toString());
                k++;
            }
            Scanner scanner = new Scanner(System.in);
            System.out.print("Opção > ");
            int opcao = scanner.nextInt();
            if (opcao < 1 || opcao > catalogoDeArtigos.size()) {
                System.out.println("Opção inválida. Por favor, selecione um número de Artigo válido.");
                runMenuAdicionarStock(compradorVendedor);
            } else {
                System.out.print("Stock a adicionar: ");
                int quantidade = scanner.nextInt();
                while (quantidade <= 0) {
                    System.out.println("A quantidade a adicionar deve ser maior que 0.");
                    System.out.print("Stock a adicionar: ");
                    quantidade = scanner.nextInt();
                }
                Artigo a = catalogoDeArtigos.get(opcao - 1);
                controller.updateCV(compradorVendedor, quantidade, opcao);
                controller.adicionarStockArtigo(a, quantidade);
                System.out.println("Stock adicionado com sucesso ao artigo selecionado.");
            }
        }
    }

    // Menu selecionar Tipo do Artigo
    public void runMenuTipoArtigo(CompradorVendedor compradorVendedor) {
        showMenuTipoArtigo(compradorVendedor);
        boolean exit = false;
        Scanner scanner = new Scanner(System.in);

        while (!exit) {
            System.out.print("Opção > ");
            int option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
                case 1 -> runMenuMala(compradorVendedor);
                case 2 -> runMenuSapatilha(compradorVendedor);
                case 3 -> runMenuTshirt(compradorVendedor);
                default -> System.out.println("Opção inválida.");
            }
        }
    }

    // Menu Adicionar Mala
    public void runMenuMala(CompradorVendedor compradorVendedor) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("\n=== Adicionar Mala ===");

        String titulo;
        do {
            System.out.print("Título: ");
            titulo = scanner.nextLine();
        } while (titulo.isEmpty());

        String descricao;
        do {
            System.out.print("Descrição: ");
            descricao = scanner.nextLine();
        } while (descricao.isEmpty());

        Random r = new Random();
        Long codigo = (long) (r.nextInt(9) + 1) * 1000000000000L + r.nextLong() % 100000000000L;

        String marca;
        do {
            System.out.print("Marca: ");
            marca = scanner.nextLine();
        } while (marca.isEmpty());

        float precoBase = 0;
        boolean precoBaseValido = false;
        while (!precoBaseValido) {
            System.out.print("Preço: ");
            if (scanner.hasNextFloat()) {
                precoBase = scanner.nextFloat();
                scanner.nextLine();
                precoBaseValido = true;
            } else {
                System.out.println("Preço inválido! Tenta novamente.");
                scanner.nextLine();
            }
        }

        int stock = 0;
        boolean stockValido = false;
        while (!stockValido) {
            System.out.print("Stock: ");
            if (scanner.hasNextInt()) {
                stock = scanner.nextInt();
                scanner.nextLine();
                stockValido = true;
            } else {
                System.out.println("Stock inválido! Tenta novamente.");
                scanner.nextLine();
            }
        }

        float dimensao = 0;
        boolean dimensaoValida = false;
        while (!dimensaoValida) {
            System.out.print("Dimensão: ");
            if (scanner.hasNextFloat()) {
                dimensao = scanner.nextFloat();
                scanner.nextLine();
                dimensaoValida = true;
            } else {
                System.out.println("Dimensão inválida! Tenta novamente.");
                scanner.nextLine();
            }
        }

        String material;
        do {
            System.out.print("Material: ");
            material = scanner.nextLine();
        } while (material.isEmpty());

        System.out.print("Ano da coleção (YYYY): ");
        int anoColecao = 0;
        boolean validInput = false;
        while (!validInput) {
            String input = scanner.nextLine();
            if (input.matches("^\\d{4}$")) {
                anoColecao = Integer.parseInt(input);
                validInput = true;
            } else {
                System.out.println("Ano de coleção inválido. Insira um ano com 4 dígitos (ex: 2022).");
            }
        }

        boolean nova = false;
        boolean novaValida = false;
        while (!novaValida) {
            System.out.print("Artigo é Novo? (Y/N): ");
            String input = scanner.nextLine().toUpperCase();
            if (input.equals("Y")) {
                nova = true;
                novaValida = true;
            } else if (input.equals("N")) {
                nova = false;
                novaValida = true;
            } else {
                System.out.println("Resposta inválida! Tenta novamente.");
            }
        }

        String condicao = "";
        float estadoUtilizacao = 0;
        int numeroDonos = 0;
        if(nova == false) {
            do {
                System.out.print("Condição: ");
                condicao = scanner.nextLine();
            } while (condicao.isEmpty());

            boolean numeroDonosValido = false;
            while (!numeroDonosValido) {
                System.out.print("Número de Donos: ");
                if (scanner.hasNextInt()) {
                    numeroDonos = scanner.nextInt();
                    scanner.nextLine();
                    numeroDonosValido = true;
                } else {
                    System.out.println("Número de Donos inválido! Tenta novamente.");
                    scanner.nextLine();
                }
            }

            boolean estadoUtilizacaoValido = false;
            while (!estadoUtilizacaoValido) {
                System.out.print("Estado de Utilização: ");
                if (scanner.hasNextFloat()) {
                    estadoUtilizacao = scanner.nextFloat();
                    scanner.nextLine();
                    estadoUtilizacaoValido = true;
                } else {
                    System.out.println("Estado de Utilização inválido! Tenta novamente.");
                    scanner.nextLine();
                }
            }
        }

        boolean premium = false;
        boolean premiumValido = false;
        while (!premiumValido) {
            System.out.print("Artigo é Premium? (Y/N): ");
            String input = scanner.nextLine().toUpperCase();
            if (input.equals("Y")) {
                premium = true;
                premiumValido = true;
            } else if (input.equals("N")) {
                premium = false;
                premiumValido = true;
            } else {
                System.out.println("Resposta inválida! Tenta novamente.");
            }
        }

        Transportador transportador = null;
        if (premium) {
            System.out.println("Selecione uma Transportadora Premium:");
            List<Transportador> transportadorasP = controller.getListaDeTransportadoresPremium();
            int i = 1;
            for (Transportador transportadora : transportadorasP) {
                System.out.println(i + "-> " + transportadora.getNome());
                i++;
            }

            int selectedTransportadora = 0;
            boolean transportadoraValida = false;
            while (!transportadoraValida) {
                System.out.print("Seleciona uma transportadora (número): ");
                if (scanner.hasNextInt()) {
                    selectedTransportadora = scanner.nextInt();
                    scanner.nextLine();
                    if (selectedTransportadora >= 1 && selectedTransportadora <= transportadorasP.size()) {
                        transportador = transportadorasP.get(selectedTransportadora - 1);
                        transportadoraValida = true;
                    } else {
                        System.out.println("Transportadora inválida! Tenta novamente.");
                    }
                } else {
                    System.out.println("Seleção inválida! Tenta novamente.");
                    scanner.nextLine();
                }
            }
        }
        else {
            System.out.println("Selecione uma Transportadora:");
            List<Transportador> transportadoras = controller.getListaDeTransportadores();
            int i = 1;
            for (Transportador transportadora : transportadoras) {
                System.out.println(i + "-> " + transportadora.getNome());
                i++;
            }

            int selectedTransportadora = 0;
            boolean transportadoraValida = false;
            while (!transportadoraValida) {
                System.out.print("Seleciona uma transportadora (número): ");
                if (scanner.hasNextInt()) {
                    selectedTransportadora = scanner.nextInt();
                    scanner.nextLine();
                    if (selectedTransportadora >= 1 && selectedTransportadora <= transportadoras.size()) {
                        transportador = transportadoras.get(selectedTransportadora - 1);
                        transportadoraValida = true;
                    } else {
                        System.out.println("Transportadora inválida! Tenta novamente.");
                    }
                } else {
                    System.out.println("Seleção inválida! Tenta novamente.");
                    scanner.nextLine();
                }
            }
        }

        if(premium && nova) {
            MalaPremium malaPremium = new MalaPremium(titulo, descricao, codigo, marca, precoBase,
                    stock, compradorVendedor ,transportador, "", 0, 10,
                    dimensao, material, anoColecao,0);
            if(controller.verificaIgualdade(compradorVendedor, malaPremium)) {
                System.out.println("A Mala já existe! Adiciona Stock.");
            } else {
                controller.adicionaArtigo(malaPremium);
                controller.adicionaArtigoProdutosVenda(compradorVendedor, malaPremium);
                controller.atualizaCompradorVendedor(compradorVendedor);
                System.out.println("Mala Nova e Premium " + malaPremium.getTitulo() + " adicionada com sucesso.");
            }
        }
        else if(premium && !nova) {
            MalaPremium malaPremium = new MalaPremium(titulo, descricao, codigo, marca, precoBase,
                    stock, compradorVendedor,transportador, condicao, numeroDonos, estadoUtilizacao,
                    dimensao, material, anoColecao, 0);
            if(controller.verificaIgualdade(compradorVendedor, malaPremium)) {
                System.out.println("A Mala já existe! Adiciona Stock.");
            } else {
                controller.adicionaArtigo(malaPremium);
                controller.adicionaArtigoProdutosVenda(compradorVendedor, malaPremium);
                controller.atualizaCompradorVendedor(compradorVendedor);
                System.out.println("Mala Premium " + malaPremium.getTitulo() + " adicionada com sucesso.");
            }
        }
        else if(!premium && nova) {
            Mala mala = new Mala(titulo, descricao, codigo, marca, precoBase,
                    stock, compradorVendedor ,transportador, "", 0,
                    10, dimensao, material, anoColecao);
            if(controller.verificaIgualdade(compradorVendedor, mala) == true) {
                System.out.println("A Mala já existe! Adiciona Stock.");
            } else {
                controller.adicionaArtigo(mala);
                controller.adicionaArtigoProdutosVenda(compradorVendedor, mala);
                controller.atualizaCompradorVendedor(compradorVendedor);
                System.out.println("Mala Nova " + mala.getTitulo() + " adicionada com sucesso.");
            }
        }
        else if(!premium && !nova) {
            Mala mala = new Mala(titulo, descricao, codigo, marca, precoBase,
                    stock, compradorVendedor ,transportador, condicao, numeroDonos, estadoUtilizacao,
                    dimensao, material, anoColecao);
            if(controller.verificaIgualdade(compradorVendedor, mala) == true) {
                System.out.println("A Mala já existe! Adiciona Stock.");
            } else {
                controller.adicionaArtigo(mala);
                controller.adicionaArtigoProdutosVenda(compradorVendedor, mala);
                controller.atualizaCompradorVendedor(compradorVendedor);
                System.out.println("Mala " + mala.getTitulo() + " adicionada com sucesso.");
            }
        }
        runMenuCompradorVendedor(compradorVendedor);
    }

    // Menu Adicionar Sapatilha
    public void runMenuSapatilha(CompradorVendedor compradorVendedor) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("\n=== Adicionar Sapatilha ===");

        String titulo;
        do {
            System.out.print("Título: ");
            titulo = scanner.nextLine();
        } while (titulo.isEmpty());

        String descricao;
        do {
            System.out.print("Descrição: ");
            descricao = scanner.nextLine();
        } while (descricao.isEmpty());

        Random r = new Random();
        Long codigo = (long) (r.nextInt(9) + 1) * 1000000000000L + r.nextLong() % 100000000000L;

        String marca;
        do {
            System.out.print("Marca: ");
            marca = scanner.nextLine();
        } while (marca.isEmpty());

        float precoBase = 0;
        boolean precoBaseValido = false;
        while (!precoBaseValido) {
            System.out.print("Preço: ");
            if (scanner.hasNextFloat()) {
                precoBase = scanner.nextFloat();
                scanner.nextLine();
                precoBaseValido = true;
            } else {
                System.out.println("Preço inválido! Tenta novamente.");
                scanner.nextLine();
            }
        }

        int stock = 0;
        boolean stockValido = false;
        while (!stockValido) {
            System.out.print("Stock: ");
            if (scanner.hasNextInt()) {
                stock = scanner.nextInt();
                scanner.nextLine();
                stockValido = true;
            } else {
                System.out.println("Stock inválido! Tenta novamente.");
                scanner.nextLine();
            }
        }

        double tamanho = 0;
        boolean tamanhoValido = false;
        while (!tamanhoValido) {
            System.out.print("Tamanho (34-47): ");
            if (scanner.hasNextDouble()) {
                tamanho = scanner.nextDouble();
                scanner.nextLine();
                if (tamanho >= 34 && tamanho <= 47) {
                    tamanhoValido = true;
                } else {
                    System.out.println("Tamanho inválido! Insira um tamanho dentro do intervalo disponível.");
                }
            } else {
                System.out.println("Tamanho inválido! Tente novamente.");
                scanner.nextLine();
            }
        }

        boolean atacadores = false;
        boolean atacadoresValidos = false;
        while (!atacadoresValidos) {
            System.out.print("Atacadores (S/N): ");
            String input = scanner.nextLine().toUpperCase();
            if (input.equals("S")) {
                atacadores = true;
                atacadoresValidos = true;
            } else if (input.equals("N")) {
                atacadores = false;
                atacadoresValidos = true;
            } else {
                System.out.println("Resposta inválida! Tenta novamente.");
            }
        }

        Color cor = null;
        boolean corValida = false;
        while (!corValida) {
            System.out.print("Cor (RED, WHITE, BLUE, GREEN, YELLOW, ORANGE, PURPLE, BLACK, GRAY): ");
            String input = scanner.nextLine();
            switch (input.toLowerCase()) {
                case "red":
                    cor = Color.RED;
                    corValida = true;
                    break;
                case "white":
                    cor = Color.WHITE;
                    corValida = true;
                    break;
                case "blue":
                    cor = Color.BLUE;
                    corValida = true;
                    break;
                case "green":
                    cor = Color.GREEN;
                    corValida = true;
                    break;
                case "yellow":
                    cor = Color.YELLOW;
                    corValida = true;
                    break;
                case "orange":
                    cor = Color.ORANGE;
                    corValida = true;
                    break;
                case "purple":
                    cor = new Color(128, 0, 128);
                    corValida = true;
                    break;
                case "black":
                    cor = Color.BLACK;
                    corValida = true;
                    break;
                case "gray":
                    cor = Color.GRAY;
                    corValida = true;
                    break;
                default:
                    System.out.println("Cor inválida! Insira uma cor válida (RED, WHITE, BLUE, GREEN, YELLOW, ORANGE, PURPLE, BLACK, GRAY, etc).");
                    break;
            }
        }


        LocalDate dataLancamento = null;
        boolean dataValida = false;
        while (!dataValida) {
            System.out.print("Data de lançamento (DD/MM/YYYY): ");
            try {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                dataLancamento = LocalDate.parse(scanner.nextLine(), formatter);
                dataValida = true;
            } catch (DateTimeParseException e) {
                System.out.println("Data de lançamento inválida! Tenta novamente.");
            }
        }

        boolean nova = false;
        boolean novaValida = false;
        while (!novaValida) {
            System.out.print("Artigo é Novo? (Y/N): ");
            String input = scanner.nextLine().toUpperCase();
            if (input.equals("Y")) {
                nova = true;
                novaValida = true;
            } else if (input.equals("N")) {
                nova = false;
                novaValida = true;
            } else {
                System.out.println("Resposta inválida! Tenta novamente.");
            }
        }

        String condicao = "";
        float estadoUtilizacao = 0;
        int numeroDonos = 0;
        if(nova == false) {
            do {
                System.out.print("Condição: ");
                condicao = scanner.nextLine();
            } while (condicao.isEmpty());

            boolean numeroDonosValido = false;
            while (!numeroDonosValido) {
                System.out.print("Número de Donos: ");
                if (scanner.hasNextInt()) {
                    numeroDonos = scanner.nextInt();
                    scanner.nextLine();
                    numeroDonosValido = true;
                } else {
                    System.out.println("Número de Donos inválido! Tenta novamente.");
                    scanner.nextLine();
                }
            }

            boolean estadoUtilizacaoValido = false;
            while (!estadoUtilizacaoValido) {
                System.out.print("Estado de Utilização: ");
                if (scanner.hasNextFloat()) {
                    estadoUtilizacao = scanner.nextFloat();
                    scanner.nextLine();
                    estadoUtilizacaoValido = true;
                } else {
                    System.out.println("Estado de Utilização inválido! Tenta novamente.");
                    scanner.nextLine();
                }
            }
        }

        boolean premium = false;
        boolean premiumValido = false;
        while (!premiumValido) {
            System.out.print("Artigo é Premium? (Y/N): ");
            String input = scanner.nextLine().toUpperCase();
            if (input.equals("Y")) {
                premium = true;
                premiumValido = true;
            } else if (input.equals("N")) {
                premium = false;
                premiumValido = true;
            } else {
                System.out.println("Resposta inválida! Tenta novamente.");
            }
        }

        Transportador transportador = null;
        if (premium == true) {
            System.out.println("Selecione uma Transportadora Premium:");
            List<Transportador> transportadorasP = controller.getListaDeTransportadoresPremium();
            int i = 1;
            for (Transportador transportadora : transportadorasP) {
                System.out.println(i + "-> " + transportadora.getNome());
                i++;
            }

            int selectedTransportadora = 0;
            boolean transportadoraValida = false;
            while (!transportadoraValida) {
                System.out.print("Seleciona uma transportadora (número): ");
                if (scanner.hasNextInt()) {
                    selectedTransportadora = scanner.nextInt();
                    scanner.nextLine();
                    if (selectedTransportadora >= 1 && selectedTransportadora <= transportadorasP.size()) {
                        transportador = transportadorasP.get(selectedTransportadora - 1);
                        transportadoraValida = true;
                    } else {
                        System.out.println("Transportadora inválida! Tenta novamente.");
                    }
                } else {
                    System.out.println("Seleção inválida! Tenta novamente.");
                    scanner.nextLine();
                }
            }
        }
        else {
            System.out.println("Selecione uma Transportadora:");
            List<Transportador> transportadoras = controller.getListaDeTransportadores();
            int i = 1;
            for (Transportador transportadora : transportadoras) {
                System.out.println(i + "-> " + transportadora.getNome());
                i++;
            }

            int selectedTransportadora = 0;
            boolean transportadoraValida = false;
            while (!transportadoraValida) {
                System.out.print("Seleciona uma transportadora (número): ");
                if (scanner.hasNextInt()) {
                    selectedTransportadora = scanner.nextInt();
                    scanner.nextLine(); // consume the newline character
                    if (selectedTransportadora >= 1 && selectedTransportadora <= transportadoras.size()) {
                        transportador = transportadoras.get(selectedTransportadora - 1);
                        transportadoraValida = true;
                    } else {
                        System.out.println("Transportadora inválida! Tenta novamente.");
                    }
                } else {
                    System.out.println("Seleção inválida! Tenta novamente.");
                    scanner.nextLine(); // consume the invalid input
                }
            }
        }

        if(premium && nova) {
            SapatilhaPremium sapatilhaPremium = new SapatilhaPremium(titulo, descricao, codigo, marca, precoBase,
                    stock, compradorVendedor,transportador, "", 0, 10, tamanho,
                    atacadores, cor, dataLancamento, 0);
            if(controller.verificaIgualdade(compradorVendedor, sapatilhaPremium)) {
                System.out.println("A Sapatilha já existe! Adiciona Stock.");
            } else {
                controller.adicionaArtigo(sapatilhaPremium);
                controller.adicionaArtigoProdutosVenda(compradorVendedor, sapatilhaPremium);
                controller.atualizaCompradorVendedor(compradorVendedor);
                System.out.println("Sapatilha Nova e Premium " + sapatilhaPremium.getTitulo() + " adicionada com sucesso.");
            }
        }
        else if(premium && !nova) {
            SapatilhaPremium sapatilhaPremium = new SapatilhaPremium(titulo, descricao, codigo, marca, precoBase,
                    stock, compradorVendedor,transportador, condicao, numeroDonos, estadoUtilizacao, tamanho,
                    atacadores, cor, dataLancamento, 0);
            if(controller.verificaIgualdade(compradorVendedor, sapatilhaPremium)) {
                System.out.println("A Sapatilha já existe! Adiciona Stock.");
            } else {
                controller.adicionaArtigo(sapatilhaPremium);
                controller.adicionaArtigoProdutosVenda(compradorVendedor, sapatilhaPremium);
                controller.atualizaCompradorVendedor(compradorVendedor);
                System.out.println("Sapatilha Premium " + sapatilhaPremium.getTitulo() + " adicionada com sucesso.");
            }
        }
        else if(!premium && nova) {
            Sapatilha sapatilha = new Sapatilha(titulo, descricao, codigo, marca, precoBase,
                    stock, compradorVendedor,transportador, "", 0, 10,
                    tamanho, atacadores, cor, dataLancamento);
            if(controller.verificaIgualdade(compradorVendedor, sapatilha)) {
                System.out.println("A Sapatilha já existe! Adiciona Stock.");
            } else {
                controller.adicionaArtigo(sapatilha);
                controller.adicionaArtigoProdutosVenda(compradorVendedor, sapatilha);
                controller.atualizaCompradorVendedor(compradorVendedor);
                System.out.println("Sapatilha Nova " + sapatilha.getTitulo() + " adicionada com sucesso.");
            }
        }
        else if(!premium && !nova) {
            Sapatilha sapatilha = new Sapatilha(titulo, descricao, codigo, marca, precoBase,
                    stock, compradorVendedor,transportador, condicao, numeroDonos, estadoUtilizacao,
                    tamanho, atacadores, cor, dataLancamento);
            if(controller.verificaIgualdade(compradorVendedor, sapatilha)) {
                System.out.println("A Sapatilha já existe! Adiciona Stock.");
            } else {
                controller.adicionaArtigo(sapatilha);
                controller.adicionaArtigoProdutosVenda(compradorVendedor, sapatilha);
                controller.atualizaCompradorVendedor(compradorVendedor);
                System.out.println("Sapatilha " + sapatilha.getTitulo() + " adicionada com sucesso.");
            }
        }
        runMenuCompradorVendedor(compradorVendedor);
    }

    // Menu Adicionar Tshirt
    public void runMenuTshirt(CompradorVendedor compradorVendedor) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("\n=== Adicionar Tshirt ===");

        String titulo;
        do {
            System.out.print("Título: ");
            titulo = scanner.nextLine();
        } while (titulo.isEmpty());

        String descricao;
        do {
            System.out.print("Descrição: ");
            descricao = scanner.nextLine();
        } while (descricao.isEmpty());

        Random r = new Random();
        Long codigo = (long) (r.nextInt(9) + 1) * 1000000000000L + r.nextLong() % 100000000000L;

        String marca;
        do {
            System.out.print("Marca: ");
            marca = scanner.nextLine();
        } while (marca.isEmpty());

        float precoBase = 0;
        boolean precoBaseValido = false;
        while (!precoBaseValido) {
            System.out.print("Preço: ");
            if (scanner.hasNextFloat()) {
                precoBase = scanner.nextFloat();
                scanner.nextLine();
                precoBaseValido = true;
            } else {
                System.out.println("Preço inválido! Tenta novamente.");
                scanner.nextLine();
            }
        }

        int stock = 0;
        boolean stockValido = false;
        while (!stockValido) {
            System.out.print("Stock: ");
            if (scanner.hasNextInt()) {
                stock = scanner.nextInt();
                scanner.nextLine();
                stockValido = true;
            } else {
                System.out.println("Stock inválido! Tenta novamente.");
                scanner.nextLine();
            }
        }

        Tshirt.Tamanho tamanho = null;
        boolean tamanhoValido = false;
        while (!tamanhoValido) {
            System.out.print("Tamanho (S/M/L/XL): ");
            String tamanhoInput = scanner.nextLine().toUpperCase();
            try {
                tamanho = Tshirt.Tamanho.valueOf(tamanhoInput);
                tamanhoValido = true;
            } catch (IllegalArgumentException e) {
                System.out.println("Tamanho inválido! Tente novamente.");
            }
        }

        Tshirt.Padrao padrao = null;
        boolean padraoValido = false;
        while (!padraoValido) {
            System.out.print("Padrão (LISO/RISCAS/PALMEIRAS): ");
            String padraoInput = scanner.nextLine().toUpperCase();
            try {
                padrao = Tshirt.Padrao.valueOf(padraoInput);
                padraoValido = true;
            } catch (IllegalArgumentException e) {
                System.out.println("Padrão inválido! Tente novamente.");
            }
        }

        boolean nova = false;
        boolean novaValida = false;
        while (!novaValida) {
            System.out.print("Artigo é Novo? (Y/N): ");
            String input = scanner.nextLine().toUpperCase();
            if (input.equals("Y")) {
                nova = true;
                novaValida = true;
            } else if (input.equals("N")) {
                nova = false;
                novaValida = true;
            } else {
                System.out.println("Resposta inválida! Tenta novamente.");
            }
        }

        String condicao = "";
        float estadoUtilizacao = 0;
        int numeroDonos = 0;
        if(nova == false) {
            do {
                System.out.print("Condição: ");
                condicao = scanner.nextLine();
            } while (condicao.isEmpty());

            boolean numeroDonosValido = false;
            while (!numeroDonosValido) {
                System.out.print("Número de Donos: ");
                if (scanner.hasNextInt()) {
                    numeroDonos = scanner.nextInt();
                    scanner.nextLine();
                    numeroDonosValido = true;
                } else {
                    System.out.println("Número de Donos inválido! Tenta novamente.");
                    scanner.nextLine();
                }
            }

            boolean estadoUtilizacaoValido = false;
            while (!estadoUtilizacaoValido) {
                System.out.print("Estado de Utilização: ");
                if (scanner.hasNextFloat()) {
                    estadoUtilizacao = scanner.nextFloat();
                    scanner.nextLine();
                    estadoUtilizacaoValido = true;
                } else {
                    System.out.println("Estado de Utilização inválido! Tenta novamente.");
                    scanner.nextLine();
                }
            }
        }

        Transportador transportador = null;
        System.out.println("Selecione uma Transportadora:");
        List<Transportador> transportadoras = controller.getListaDeTransportadores();
        int i = 1;
        for (Transportador transportadora : transportadoras) {
            System.out.println(i + "-> " + transportadora.getNome());
            i++;
        }

        int selectedTransportadora = 0;
        boolean transportadoraValida = false;
        while (!transportadoraValida) {
            System.out.print("Seleciona uma transportadora (número): ");
            if (scanner.hasNextInt()) {
                selectedTransportadora = scanner.nextInt();
                scanner.nextLine();
                if (selectedTransportadora >= 1 && selectedTransportadora <= transportadoras.size()) {
                    transportador = transportadoras.get(selectedTransportadora - 1);
                    transportadoraValida = true;
                } else {
                    System.out.println("Transportadora inválida! Tenta novamente.");
                }
            } else {
                System.out.println("Seleção inválida! Tenta novamente.");
                scanner.nextLine();
            }
        }

        if(nova) {
            if(padrao == Tshirt.Padrao.LISO) {
                Tshirt tshirt = new Tshirt(titulo, descricao, codigo, marca, precoBase,
                        stock, compradorVendedor ,transportador, tamanho, padrao, 0,
                        "", 0, 10);
                if(controller.verificaIgualdade(compradorVendedor, tshirt)) {
                    System.out.println("A Tshirt já existe! Adiciona Stock.");
                } else {
                    controller.adicionaArtigo(tshirt);
                    controller.adicionaArtigoProdutosVenda(compradorVendedor, tshirt);
                    controller.atualizaCompradorVendedor(compradorVendedor);
                    System.out.println("Tshirt Nova " + tshirt.getTitulo() + " adicionada com sucesso.");
                }
            }
            else {
                Tshirt tshirt = new Tshirt(titulo, descricao, codigo, marca, precoBase,
                        stock, compradorVendedor,transportador, tamanho, padrao, 0,
                        "", 0, 10);
                if(controller.verificaIgualdade(compradorVendedor, tshirt)) {
                    System.out.println("A Tshirt já existe! Adiciona Stock.");
                } else {
                    controller.adicionaArtigo(tshirt);
                    controller.adicionaArtigoProdutosVenda(compradorVendedor, tshirt);
                    controller.atualizaCompradorVendedor(compradorVendedor);
                    System.out.println("Tshirt Nova " + tshirt.getTitulo() + " adicionada com sucesso.");
                }
            }
        }
        else if(!nova) {
            if(padrao == Tshirt.Padrao.LISO) {
                Tshirt tshirt = new Tshirt(titulo, descricao, codigo, marca, precoBase,
                        stock, compradorVendedor,transportador, tamanho, padrao, 0,
                        condicao, numeroDonos, estadoUtilizacao);
                if(controller.verificaIgualdade(compradorVendedor, tshirt)) {
                    System.out.println("A Tshirt já existe! Adiciona Stock.");
                } else {
                    controller.adicionaArtigo(tshirt);
                    controller.adicionaArtigoProdutosVenda(compradorVendedor, tshirt);
                    controller.atualizaCompradorVendedor(compradorVendedor);
                    System.out.println("Tshirt " + tshirt.getTitulo() + " adicionada com sucesso.");
                }
            }
            else {
                Tshirt tshirt = new Tshirt(titulo, descricao, codigo, marca, precoBase,
                        stock, compradorVendedor ,transportador, tamanho, padrao, 50,
                        "", 0, 10);
                if(controller.verificaIgualdade(compradorVendedor, tshirt)) {
                    System.out.println("A Tshirt já existe! Adiciona Stock.");
                } else {
                    controller.adicionaArtigo(tshirt);
                    controller.adicionaArtigoProdutosVenda(compradorVendedor, tshirt);
                    controller.atualizaCompradorVendedor(compradorVendedor);
                    System.out.println("Tshirt " + tshirt.getTitulo() + " adicionada com sucesso.");
                }
            }
        }
        runMenuCompradorVendedor(compradorVendedor);
    }

    // Opção 5 do Menu CompradorVendedor
    public void runMenuRemoveArtigoCompradorVendedor(CompradorVendedor compradorVendedor) {
        int size = compradorVendedor.getProdutosVenda().size();
        if (size == 0) {
            System.out.println("Sem Artigos publicados, adiciona primeiro!");
            return;
        }

        showMenuRemoveArtigo(compradorVendedor);
        boolean exit = false;
        Scanner scanner = new Scanner(System.in);

        while (!exit) {
            System.out.print("Opção (0-" + size + ") > ");
            int option = scanner.nextInt();
            scanner.nextLine();

            if (option < 0 || option > size) {
                System.out.println("Opção inválida. Por favor, insira um valor entre 0 e " + size);
            } else if (option == 0) {
                exit = true;
            } else {
                Artigo artigo = controller.getArtigoCompradorVendedor(compradorVendedor, option);
                System.out.println(artigo);

                int currentStock = artigo.getStock();
                if (currentStock <= 0) {
                    System.out.println("Não há stock disponível para este artigo!");
                } else if (currentStock == 1) {
                    controller.removeArtigoCompradorVendedor(compradorVendedor, artigo);

                    controller.removeArtigo(artigo);

                    controller.atualizaCompradorVendedor(compradorVendedor);

                    System.out.println("Foi removido o Artigo " + artigo.getTitulo() + ".");
                    break;
                } else {
                    boolean validInput = false;
                    int quantidadeRemover = 0;

                    while (!validInput) {
                        System.out.print("Quantidade a remover (Máx: " + currentStock + "): ");
                        try {
                            quantidadeRemover = scanner.nextInt();
                            scanner.nextLine();

                            if (quantidadeRemover <= 0) {
                                System.out.println("A quantidade deve ser um número positivo!");
                            } else if (quantidadeRemover > currentStock) {
                                System.out.println("A quantidade não pode ser superior ao stock atual (" + currentStock + ")!");
                            } else {
                                validInput = true;
                            }
                        } catch (InputMismatchException e) {
                            System.out.println("Input inválido. Insere um número inteiro válido.");
                            scanner.nextLine();
                        }
                    }

                    if (quantidadeRemover == currentStock) {
                        controller.removeArtigoCompradorVendedor(compradorVendedor, artigo);

                        controller.removeArtigo(artigo);

                        controller.atualizaCompradorVendedor(compradorVendedor);

                        System.out.println("Foram removidos " + quantidadeRemover + " unidades do Artigo " + artigo.getTitulo() + ".");
                    } else {
                        int stockManter = currentStock - quantidadeRemover;
                        controller.adicionarStockArtigo(artigo, quantidadeRemover);

                        List<Artigo> listaVenda = compradorVendedor.getProdutosVenda();
                        Artigo artigoAux = artigo.clone();
                        artigoAux.setStock(stockManter);
                        int index = listaVenda.indexOf(artigo);
                        listaVenda.set(index, artigoAux);

                        compradorVendedor.setProdutosVenda(listaVenda);

                        controller.atualizaCompradorVendedor(compradorVendedor);

                        if(quantidadeRemover == 1) {
                            System.out.println("Foi removido " + quantidadeRemover + " unidade do Artigo " + artigo.getTitulo() + ".");
                        }
                        else {
                            System.out.println("Foram removidos " + quantidadeRemover + " unidades do Artigo " + artigo.getTitulo() + ".");
                        }
                    }
                }
            }
        }
        runMenuCompradorVendedor(compradorVendedor);
    }

    // Menus Prints
    // Interface do Menu Inicial
    public void showMenuInicial() {
        String data = this.controller.getTempoDoSistema().toString();
        System.out.println("\n+---------------------------------------------------------------+");
        System.out.println("| Vintage" + "                                     Data : " + data + " |");
        System.out.println("+---------------------------------------------------------------+");
        System.out.println("| 1 -> Iniciar Sessão                                           |");
        System.out.println("| 2 -> Registar                                                 |");
        System.out.println("| 0 -> Sair                                                     |");
        System.out.println("+---------------------------------------------------------------+");
    }

    // Interface do Menu de Registar Utilizador
    public void showMenuRegistarUtilizador() {
        String data = this.controller.getTempoDoSistema().toString();
        System.out.println("\n+---------------------------------------------------------------+");
        System.out.println("| Utilizador" + "                                  Data : " + data + " |");
        System.out.println("+---------------------------------------------------------------+");
        System.out.println("| 1 -> CompradorVendedor                                        |");
        System.out.println("| 2 -> Transportador                                            |");
        System.out.println("| 0 -> Sair                                                     |");
        System.out.println("+---------------------------------------------------------------+");
    }

    // Interface do Menu Principal do CompradorVendedor
    public void showMenuCompradorVendedor(CompradorVendedor compradorVendedor) {
        String data = this.controller.getTempoDoSistema().toString();
        System.out.println("\n+---------------------------------------------------------------+");
        System.out.println("| CompradorVendedor : " + compradorVendedor.getNome() + "          Data : " + data);
        System.out.println("+---------------------------------------------------------------+");
        System.out.println("| Comprador                                                     |");
        System.out.println("+---------------------------------------------------------------+");
        System.out.println("| 1 -> Ver e/ou Adicionar Artigos                               |");
        System.out.println("| 2 -> Consultar o seu Carrinho de Compras                      |");
        System.out.println("| 3 -> Consultar as Encomendas em Exportação                    |");
        System.out.println("| 4 -> Devolver uma Encomenda                                   |");
        System.out.println("+---------------------------------------------------------------+");
        System.out.println("| Vendedor                                                      |");
        System.out.println("+---------------------------------------------------------------+");
        System.out.println("| 5 -> Consultar os seus Produtos à venda                       |");
        System.out.println("| 6 -> Adicionar Artigo para Venda ou Adicionar Stock           |");
        System.out.println("| 7 -> Remover Artigo do Anúncio                                |");
        System.out.println("+---------------------------------------------------------------+");
        System.out.println("| 8 -> Consultar Faturas                                        |");
        System.out.println("| 9 -> Avançar no Tempo                                         |");
        System.out.println("| 0 -> Terminar Sessão                                          |");
        System.out.println("+---------------------------------------------------------------+");
    }

    // Interface do Menu Principal do Admin (Onde se pode obter informações sobre as Stats)
    public void showMenuAdmin(Admin admin) {
        String data = this.controller.getTempoDoSistema().toString();
        System.out.println("\n+---------------------------------------------------------------------------+");
        System.out.println("| Admin : " + admin.getNome() + "           Data : " + data);
        System.out.println("+---------------------------------------------------------------------------+");
        System.out.println("| 1 -> Qual é o Vendedor que mais faturou desde sempre.                     |");
        System.out.println("| 2 -> Qual é o Vendedor que mais faturou num determinado periodo.          |");
        System.out.println("| 3 -> Qual é o Transportador com maior volume de faturação.                |");
        System.out.println("| 4 -> Listar os Artigos vendidos por um Vendedor.                          |");
        System.out.println("| 5 -> Obter uma ordenação dos maiores Compradores desde sempre.            |");
        System.out.println("| 6 -> Obter uma ordenação dos maiores Compradores num determinado periodo. |");
        System.out.println("| 7 -> Obter uma ordenação dos maiores Vendedores desde sempre.             |");
        System.out.println("| 8 -> Obter uma ordenação dos maiores Vendedores num determinado período.  |");
        System.out.println("| 9 -> Determinar quanto dinheiro ganhou a Vintage no seu funcionamento.    |");
        System.out.println("| 0 -> Terminar Sessão.                                                     |");
        System.out.println("+---------------------------------------------------------------------------+");
    }

    // Interface do Menu do CompradorVendedor para agir sobre o seu Carrinho de Compras
    public void showConsultarCarrinhoCompras(CompradorVendedor compradorVendedor) {
        System.out.println("+---------------------------------------------------------------+");
        System.out.println("| 1 -> Finalizar Compra                                         |");
        System.out.println("| 2 -> Remover Artigo                                           |");
        System.out.println("| 0 -> Voltar Atrás                                             |");
        System.out.println("+---------------------------------------------------------------+");
    }

    // Interface do Menu Principal do Transportador
    public void showMenuTransportador(Transportador transportador) {
        String data = this.controller.getTempoDoSistema().toString();
        System.out.println("\n+---------------------------------------------------------------+");
        if(transportador.isTransportePremium()) {
            System.out.println("| Transportador Premium " + transportador.getNome() + "           Data : " + data);
        } else {
            System.out.println("| Transportador " + transportador.getNome() + "          Data : " + data);
        }
        System.out.println("+---------------------------------------------------------------+");
        System.out.println("| 1 -> Consultar artigos por entregar                           |");
        System.out.println("| 2 -> Alterar Valores da Fórmula                               |");
        System.out.println("| 3 -> Alterar Fórmula                                          |");
        System.out.println("| 0 -> Terminar Sessão                                          |");
        System.out.println("+---------------------------------------------------------------+");
    }

    // Interface do Menu do Transportador que lhe permite alterar a fórmula do preço do transporte
    public void showMenuAlterarFormula(Transportador transportador) {
        String data = this.controller.getTempoDoSistema().toString();
        System.out.println("\n+---------------------------------------------------------------+");
        if(transportador.isTransportePremium()) {
            System.out.println("| Transportador Premium" + transportador.getNome() + "           Data : " + data);
        } else {
            System.out.println("| Transportador " + transportador.getNome() + "           Data : " + data);
        }
        System.out.println("+---------------------------------------------------------------+");
        System.out.println("| Alterar Valores da Fórmula: " + transportador.getFormula());
        System.out.println("+---------------------------------------------------------------+");
        System.out.println("| 1 -> Valor Base Pequena : " + transportador.getValorBaseExpedicaoPequena());
        System.out.println("| 2 -> Valor Base Media : " + transportador.getValorBaseExpedicaoMedia());
        System.out.println("| 3 -> Valor Base Grande : " + transportador.getValorBaseExpedicaoGrande());
        System.out.println("| 4 -> Margem Lucrativa do Transportador: " + transportador.getMargemLucro());
        System.out.println("| 5 -> Imposto: " + transportador.getFatorMultiplicativoImpostos());
        System.out.println("| 0 -> Voltar Atrás");
        System.out.println("+---------------------------------------------------------------+");
    }

    // Interface do Menu do CompradorVendedor que lhe permite por um artigo a venda ou aumentar o stock de um
    // produto que já tenha a venda
    public void showMenuSelecionarArtigo(CompradorVendedor compradorVendedor) {
        String data = this.controller.getTempoDoSistema().toString();
        System.out.println("\n+---------------------------------------------------------------+");
        System.out.println("| CompradorVendedor " + compradorVendedor.getNome() + "          Data : " + data);
        System.out.println("+---------------------------------------------------------------+");
        System.out.println("| Deseja adicionar Stock a um Artigo ou Adicionar um Artigo?    |");
        System.out.println("+---------------------------------------------------------------+");
        System.out.println("| 1 -> Adicionar Stock                                          |");
        System.out.println("| 2 -> Adicionar Artigo                                         |");
        System.out.println("| 0 -> Voltar Atrás                                             |");
        System.out.println("+---------------------------------------------------------------+");
    }

    // Interface do Menu do Comprador Vendedor que lhe permite escolher o tipo de Artigo que pretende por a venda
    public void showMenuTipoArtigo(CompradorVendedor compradorVendedor) {
        String data = this.controller.getTempoDoSistema().toString();
        System.out.println("\n+---------------------------------------------------------------+");
        System.out.println("| CompradorVendedor " + compradorVendedor.getNome() + "          Data : " + data);
        System.out.println("+---------------------------------------------------------------+");
        System.out.println("| Seleciona o Tipo de Artigo                                    |");
        System.out.println("| 1 -> Mala                                                     |");
        System.out.println("| 2 -> Sapatilha                                                |");
        System.out.println("| 3 -> Tshirt                                                   |");
        System.out.println("+---------------------------------------------------------------+");
    }

    // Interface do Menu do CompradorVendedor que lhe permite adicionar um Artigo ao seu Carrinho de Compras
    public void showAdicionarArtigo(CompradorVendedor compradorVendedor) {
        String data = this.controller.getTempoDoSistema().toString();
        System.out.println("\n+---------------------------------------------------------------+");
        System.out.println("| CompradorVendedor " + compradorVendedor.getNome() + "          Data : " + data);
        System.out.println("+---------------------------------------------------------------+");
        System.out.println("| Qual o Artigo que deseja adicionar ao Carrinho?               |");
        System.out.println("| 0 -> Voltar Atrás                                             |");
        System.out.println("+---------------------------------------------------------------+");
    }

    // Interface do Menu do CompradorVendedor que lhe permite escolher um artigo para remover do seu
    // Carrinho de Compras
    public void showMenuRemoveArtigo(CompradorVendedor compradorVendedor) {
        String data = this.controller.getTempoDoSistema().toString();
        System.out.println("\n+---------------------------------------------------------------+");
        System.out.println("| CompradorVendedor " + compradorVendedor.getNome() + "          Data : " + data);
        System.out.println("+---------------------------------------------------------------+");
        System.out.println("| Qual o Artigo que deseja remover?                             |");
        System.out.println("| 0 -> Voltar Atrás                                             |");
        System.out.println("+---------------------------------------------------------------+");
    }
}