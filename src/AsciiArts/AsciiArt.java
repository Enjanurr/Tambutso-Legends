package AsciiArts;

public class AsciiArt {
    public static final String RESET  = "\u001B[0m";

    public static final String RED_BOLD    = "\033[1;31m";
    public static final String GREEN   = "\u001B[32m";
    public static final String CYAN = "\u001B[36m";
    public static final String ORANGE = "\u001B[38;2;255;165;0m";
    public static final String DARK_ORANGE = "\u001B[38;2;255;140;0m";
    public static final String DARK_GREEN = "\u001B[38;2;0;100;0m";
    public static final String YELLOW = "\u001B[33m";

    //TITLE ART
    public static void printTitleArt1() {
        System.out.println(GREEN);
        System.out.println("    ████████████   █████████   ██████   ██████ ███████████   █████  █████ ███████████  █████████     ███████ ");
        System.out.println("    ▒█▒▒▒███▒▒▒█  ███▒▒▒▒▒███ ▒▒██████ ██████ ▒▒███▒▒▒▒▒███ ▒▒███  ▒▒███ ▒█▒▒▒███▒▒▒█ ███▒▒▒▒▒███  ███▒▒▒▒▒███");
        System.out.println("    ▒   ▒███  ▒  ▒███    ▒███  ▒███▒█████▒███  ▒███    ▒███  ▒███   ▒███ ▒   ▒███  ▒ ▒███    ▒▒▒  ███     ▒▒███");
        System.out.println("        ▒███     ▒███████████  ▒███▒▒███ ▒███  ▒██████████   ▒███   ▒███     ▒███    ▒▒█████████ ▒███      ▒███");
        System.out.println("        ▒███     ▒███▒▒▒▒▒███  ▒███ ▒▒▒  ▒███  ▒███▒▒▒▒▒███  ▒███   ▒███     ▒███     ▒▒▒▒▒▒▒▒███▒███      ▒███");
        System.out.print(ORANGE);
        System.out.println("        ▒███     ▒███    ▒███  ▒███      ▒███  ▒███    ▒███  ▒███   ▒███     ▒███     ███    ▒███▒▒███     ███");
        System.out.println("        █████    █████   █████ █████     █████ ███████████   ▒▒████████      █████   ▒▒█████████  ▒▒▒███████▒");
    }

    public static void printTitleArt2() {
        System.out.println("            ▒█████      ▒██████████     █████████   ██████████  ██████   █████ ██████████     █████████ ");
        System.out.println("            ▒▒███       ▒▒███▒▒▒▒▒█    ███▒▒▒▒▒███ ▒▒███▒▒▒▒▒█ ▒▒██████ ▒▒███ ▒▒███▒▒▒▒███   ███▒▒▒▒▒███");
        System.out.println("             ▒███        ▒███  █ ▒   ▒███     ▒▒▒   ▒███  █ ▒   ▒███▒███ ▒███  ▒███   ▒▒███ ▒███    ▒▒▒");
        System.out.print(RED_BOLD);
        System.out.println("             ▒███        ▒██████     ▒███           ▒██████     ▒███▒▒███▒███  ▒███    ▒███ ▒▒█████████");
        System.out.println("             ▒███        ▒███▒▒█     ▒███    █████  ▒███▒▒█     ▒███ ▒▒██████  ▒███    ▒███  ▒▒▒▒▒▒▒▒███");
        System.out.println("             ▒███      █ ▒███ ▒     █▒▒███  ▒▒███   ▒███ ▒   █  ▒███  ▒▒█████  ▒███    ███   ███    ▒███");
        System.out.println("            ▒███████████ ██████████   ▒▒█████████   ██████████  █████  ▒▒█████ ██████████   ▒▒█████████");
        System.out.println(RESET);
    }

    // MAP 1 JEEPNEY
    public static void printMap1Jeepney() {
        System.out.println("\n\n\n");
        System.out.print(GREEN);
        System.out.println("                      ____________________________________________________|||          |              ");
        System.out.println("                      ====================================================|||          |              ");
        System.out.println("                       |=====\\ | ,~~. |       |       |       | ,~~. |     \\           |            ");
        System.out.println("                       |______\\| |  | |_______|_______|_______| |  | |   ,~``~.        |             ");
        System.out.println("                         _|, .    '..' '-----------------------' '..' |  /  ,.  \\-------|-,           ");
        System.out.println("                       / |          {NAGA           MINGLANILLA}      \\_\\  `'  /. _____|_\\_  |     ");
        System.out.println("                       {||                                                              `;_\\ |       ");
        System.out.println("                       {|]          ,~``~.                                        ,~``~.=====|        ");
        System.out.println("                       [_[__(______/  ,.  \\__)================================== /  ,.  \\----|      ");
        System.out.println("                       [_______/   \\  `'  /                                      \\  `'  /           ");
        System.out.println("                                    \"~..~\"                                        `~..~\"           ");
        System.out.print(RESET);
        System.out.println("\n\n\n");
    }

    // MAP 2 JEEPNEY
    public static void printMap2Jeepney() {
        System.out.println("\n\n\n");
        System.out.print(ORANGE);
        System.out.println("                      ____________________________________________________|||          |                                        ");
        System.out.println("                      ====================================================|||          |                                        ");
        System.out.println("                       |=====\\ | ,~~. |       |       |       | ,~~. |     \\           |                                        ");
        System.out.println("                       |______\\| |  | |_______|_______|_______| |  | |   ,~``~.        |                                        ");
        System.out.println("                         _|, .    '..' '-----------------------' '..' |  /  ,.  \\-------|-,                                       ");
        System.out.println("                        / |          {MINGLANILLA         CIT-U}      \\_\\  `'  /. _____|_\\_  |                                    ");
        System.out.println("                        {||                                                              `;_\\ |                                    ");
        System.out.println("                        {|]          ,~``~.                                        ,~``~.=====|                                    ");
        System.out.println("                        [_[__(______/  ,.  \\__)================================== /  ,.  \\----|                                    ");
        System.out.println("                        [_______/   \\  `'  /                                      \\  `'  /                                        ");
        System.out.println("                                     \"~..~\"                                        `~..~\"                                         ");
        System.out.print(RESET);
        System.out.println("\n\n\n");
    }

    // MAP 3 JEEPNEY
    public static void printMap3Jeepney() {
        System.out.println("\n\n\n");
        System.out.print(RED_BOLD);
        System.out.println("                      ____________________________________________________|||          |                                        ");
        System.out.println("                      ====================================================|||          |                                        ");
        System.out.println("                       |=====\\ | ,~~. |       |       |       | ,~~. |     \\           |                                        ");
        System.out.println("                       |______\\| |  | |_______|_______|_______| |  | |   ,~``~.        |                                        ");
        System.out.println("                         _|, .    '..' '-----------------------' '..' |  /  ,.  \\-------|-,                                       ");
        System.out.println("                        / |          {CIT-U             IT PARK}      \\_\\  `'  /. _____|_\\_  |                                    ");
        System.out.println("                        {||                                                              `;_\\ |                                    ");
        System.out.println("                        {|]          ,~``~.                                        ,~``~.=====|                                    ");
        System.out.println("                        [_[__(______/  ,.  \\__)================================== /  ,.  \\----|                                    ");
        System.out.println("                        [_______/   \\  `'  /                                      \\  `'  /                                        ");
        System.out.println("                                     \"~..~\"                                        `~..~\"                                         ");
        System.out.print(RESET);
        System.out.println("\n\n\n");
    }

    // LOADING ART (BEFORE EVERY MAP)
    public static void printSunrise() {
        System.out.println("\n\n\n");
        System.out.print(ORANGE);
        System.out.println("                                                       @@@@@@@                                ");
        System.out.println("                                                    @@@@@@@@@@@@@                             ");
        System.out.println("                                                  @@@@@@@@@@@@@@@@@                           ");
        System.out.println("                                                 @@@@@@@@@@@@@@@@@@@                          ");
        System.out.println("                                                @@@@@@@@@@@@@@@@@@@@@                         ");
        System.out.print(CYAN);
        System.out.println("                      ~~~~ ~~ ~~~~~ ~~~~~~~~ ~~ " + DARK_ORANGE + "&&&&&&&&&&&&&&&&&&&&&" + CYAN + " ~~~~~~~ ~~~~~~~~~~~ ~~~ ");
        System.out.println("                      ~         ~~   ~  ~      " + DARK_ORANGE + " ~~~~~~~~~~~~~~~~~~~~ " + CYAN + "  ~       ~~     ~~ ~      ");
        System.out.println("                        ~      ~~      ~~ ~~ ~~  " + DARK_ORANGE + "~~~~~~~~~~~~~ ~~~~  "+ CYAN + "      ~~~    ~ ~~~  ~ ~~ ");
        System.out.println("                        ~  ~~     ~         ~     " + DARK_ORANGE + "~~~~~~  ~~ ~~~" + CYAN + "      ~~ ~ ~~  ~~ ~         ");
        System.out.println("                      ~  ~       ~ ~      ~          " + DARK_ORANGE + "~~ ~~~~~~  ~" + CYAN + "      ~~  ~             ~~  ");
        System.out.println("                         ~             ~        ~      ~      ~~   ~             ~            ");
        System.out.print(RESET);
        System.out.println("\n\n\n");
    }

    // LOADING ART (AFTER EVERY MAP COMPLETION)
    public static void printSunset() {
        System.out.println("\n\n\n");
        System.out.println("                                                                                                                                        ");
        System.out.println("                                                                                                                     ");
        System.out.println(ORANGE);
        System.out.println("                                                       @@@@@@@                                                  ");
        System.out.println("                                                    @@@@@@@@@@@@@                                                 ");
        System.out.print(CYAN);
        System.out.println("                      ~~~~ ~~ ~~~~~ ~~~~~~~~ ~~ ~~~ " + DARK_ORANGE + "&&&&&&&&&&&&&" + CYAN + " ~~  ~~~~~~~ ~~~~~~~~~~~ ~~~         ");
        System.out.println("                      ~         ~~   ~ ~   ~~  ~~   " + DARK_ORANGE + "~~ ~~~~~~~~ ~" + CYAN +"    ~ ~  ~~ ~       ~~     ~         ");
        System.out.println("                        ~      ~~      ~~ ~~ ~~   ~~   " + DARK_ORANGE + "~~~ ~~~" + CYAN + "     ~     ~~~    ~ ~~~  ~ ~~           ");
        System.out.println("                        ~  ~~     ~         ~~  ~~   ~         ~ ~       ~~ ~ ~~  ~~ ~                ");
        System.out.println("                      ~  ~       ~ ~      ~           ~~ ~~ ~~  ~      ~~  ~             ~~           ");
        System.out.println("                            ~             ~        ~      ~      ~~   ~             ~                 ");
        System.out.print(RESET);
        System.out.println("\n\n\n");
    }

    public static void printEncounter() {
        System.out.println("\n\n\n");
        System.out.println(RED_BOLD);
        System.out.println("▄████▄ ▄▄  ▄▄   ██████ ▄▄  ▄▄ ▄▄▄▄▄ ▄▄   ▄▄ ▄▄ ▄▄   ▄████▄ ▄▄▄▄  ▄▄▄▄  ▄▄▄▄   ▄▄▄   ▄▄▄   ▄▄▄▄ ▄▄ ▄▄ ▄▄▄▄▄  ▄▄▄▄      ");
        System.out.println("██▄▄██ ███▄██   ██▄▄   ███▄██ ██▄▄  ██▀▄▀██ ▀███▀   ██▄▄██ ██▄█▀ ██▄█▀ ██▄█▄ ██▀██ ██▀██ ██▀▀▀ ██▄██ ██▄▄  ███▄▄      ");
        System.out.println("██  ██ ██ ▀██   ██▄▄▄▄ ██ ▀██ ██▄▄▄ ██   ██   █     ██  ██ ██    ██    ██ ██ ▀███▀ ██▀██ ▀████ ██ ██ ██▄▄▄ ▄▄██▀ ▄ ▄ ▄");
        System.out.println(RESET);
        System.out.println("\n\n\n");
    }

    // after previous anim
    public static void printFinaleEncounter() {
        System.out.println("\n\n\n");
        System.out.println(RED_BOLD);
        System.out.println("                                                   ███     █████████");
        System.out.println("                                                  ▒███   ▒███▒▒  ▒███");
        System.out.println("                                                  ▒███           ▒███");
        System.out.println("                                                  ▒███         ██████");
        System.out.println("                                                  ▒███       ▒████   ");
        System.out.println("                                                  ▒███     ▒████     ");
        System.out.println("                                                  ▒███     ▒███      ");
        System.out.println();
        System.out.println("                                                  ▒███     ▒███      ");
        System.out.println(RESET);
        System.out.println("\n\n\n");
    }

// BOSS TITLE ARTS

    public static void printVaughnTitleArt() {
        System.out.println("\n\n\n");
        System.out.print(DARK_GREEN);
        System.out.println("                            ▄▄    ▄▄                                ▄▄                 ");
        System.out.println("                            ▀██  ██▀                                ██                 ");
        System.out.println("                             ██  ██    ▄█████▄  ██    ██   ▄███▄██  ██▄████▄  ██▄████▄ ");
        System.out.println("                             ██  ██    ▀ ▄▄▄██  ██    ██  ██▀  ▀██  ██▀   ██  ██▀   ██ ");
        System.out.println("                              ████    ▄██▀▀▀██  ██    ██  ██    ██  ██    ██  ██    ██ ");
        System.out.println("                              ████    ██▄▄▄███  ██▄▄▄███  ▀██▄▄███  ██    ██  ██    ██ ");
        System.out.println("                              ▀▀▀▀     ▀▀▀▀ ▀▀   ▀▀▀▀ ▀▀   ▄▀▀▀ ██  ▀▀    ▀▀  ▀▀    ▀▀ ");
        System.out.println("                                                           ▀████▀▀                     ");
        System.out.println();
        System.out.println("                        ___      ___     __   __        __      __                     ");
        System.out.println("                         |  |__| |__     |__) /  \\  /\\  |  \\    |__) |  | |    |    \\ / ");
        System.out.println("                         |  |  | |___    |  \\ \\__/ /~~\\ |__/    |__) \\__/ |___ |___  |  ");
        System.out.print(RESET);
        System.out.println("\n\n\n");
    }

    public static void printAdrianTitleArt() {
        System.out.println("\n\n\n");
        System.out.print(DARK_ORANGE);
        System.out.println("                               ▄▄           ▄▄               ██                        ");
        System.out.println("                              ████          ██               ▀▀                        ");
        System.out.println("                              ████     ▄███▄██   ██▄████   ████      ▄█████▄  ██▄████▄ ");
        System.out.println("                             ██  ██   ██▀  ▀██   ██▀         ██      ▀ ▄▄▄██  ██▀   ██ ");
        System.out.println("                             ██████   ██    ██   ██          ██     ▄██▀▀▀██  ██    ██ ");
        System.out.println("                            ▄██  ██▄  ▀██▄▄███   ██       ▄▄▄██▄▄▄  ██▄▄▄███  ██    ██ ");
        System.out.println("                            ▀▀    ▀▀    ▀▀▀ ▀▀   ▀▀       ▀▀▀▀▀▀▀▀   ▀▀▀▀ ▀▀  ▀▀    ▀▀ ");
        System.out.println();
        System.out.println("               ___       ___     __   ___  __             ___  __   __      __        __   ___  __  ");
        System.out.println("                |  |__| |__     |__) |__  /  ` |__/ |    |__  /__` /__`    |__)  /\\  /  ` |__  |__) ");
        System.out.println("                |  |  | |___    |  \\ |___ \\__, |  \\ |___ |___ .__/ .__/    |  \\ /~~\\ \\__, |___ |  \\ ");
        System.out.print(RESET);
        System.out.println("\n\n\n");
    }

    public static void printSirKhaiTitleArt() {
        System.out.println("\n\n\n");
        System.out.print(RED_BOLD);
        System.out.println("                       ▄▄▄▄       ██                    ▄▄   ▄▄▄  ▄▄                     ██    ");
        System.out.println("                     ▄█▀▀▀▀█      ▀▀                    ██  ██▀   ██                     ▀▀    ");
        System.out.println("                     ██▄        ████      ██▄████       ██▄██     ██▄████▄   ▄█████▄   ████   ");
        System.out.println("                      ▀████▄      ██      ██▀           █████     ██▀   ██   ▀ ▄▄▄██     ██    ");
        System.out.println("                          ▀██     ██      ██            ██  ██▄   ██    ██  ▄██▀▀▀██     ██    ");
        System.out.println("                     █▄▄▄▄▄█▀  ▄▄▄██▄▄▄   ██            ██   ██▄  ██    ██  ██▄▄▄███  ▄▄▄██▄▄▄ ");
        System.out.println("                      ▀▀▀▀▀    ▀▀▀▀▀▀▀▀   ▀▀            ▀▀    ▀▀  ▀▀    ▀▀   ▀▀▀▀ ▀▀  ▀▀▀▀▀▀▀▀ ");
        System.out.println();
        System.out.println("                     ___       ___                 __      __   ___     __   __        __   __  ");
        System.out.println("                      |  |__| |__     |__/ | |\\ | / _`    /  \\ |__     |__) /  \\  /\\  |  \\ /__` ");
        System.out.println("                      |  |  | |___    |  \\ | | \\| \\__>    \\__/ |       |  \\ \\__/ /~~\\ |__/ .__/ ");
        System.out.print(RESET);
        System.out.println("\n\n\n");
    }

    public static void printJolliKhaiTitleArt() {
        System.out.println("\n\n\n");
        System.out.println("                 ▄█  ▄██████▄   ▄█        ▄█        ▄█     ▄█   ▄█▄    ▄█    █▄       ▄████████  ▄█");
        System.out.println("                ███ ███    ███ ███       ███       ███    ███ ▄███▀   ███    ███     ███    ███ ███");
        System.out.println("                ███ ███    ███ ███       ███       ███▌   ███▐██▀     ███    ███     ███    ███ ███▌");
        System.out.print(YELLOW);
        System.out.println("                ███ ███    ███ ███       ███       ███▌  ▄█████▀     ▄███▄▄▄▄███▄▄   ███    ███ ███▌");
        System.out.println("                ███ ███    ███ ███       ███       ███▌ ▀▀█████▄    ▀▀███▀▀▀▀███▀  ▀███████████ ███▌");
        System.out.println("                ███ ███    ███ ███       ███       ███    ███▐██▄     ███    ███     ███    ███ ███");
        System.out.print(RED_BOLD);
        System.out.println("                ███ ███    ███ ███▌    ▄ ███▌    ▄ ███    ███ ▀███▄   ███    ███     ███    ███ ███");
        System.out.println("            █▄ ▄███  ▀██████▀  █████▄▄██ █████▄▄██ █▀     ███   ▀█▀   ███    █▀      ███    █▀  █▀");
        System.out.println("            ▀▀▀▀▀▀             ▀         ▀                ▀                                         ");
        System.out.println(RESET);
        System.out.println("         _____ __  __ _____    ____  _____ _____         ____  _____ __ __ _____  ____  __ __  __  ____  ");
        System.out.print(YELLOW);
        System.out.println("          ||   ||==|| ||==     ||=)  ||==  ||==   ====  (( ___ ||_// || || ||  ) (( ___ || ||\\ || (( ___ ");
        System.out.print(RED_BOLD);
        System.out.println("          ||   ||  || ||___    ||_)) ||___ ||___         \\\\_|| || \\\\ \\\\_// ||_//  \\\\_|| || || \\||  \\\\_|| ");
        System.out.print(RESET);
        System.out.println("\n\n\n");
    }

    public static void printVictoryArt() {
        System.out.println("\n\n\n");
        System.out.print(ORANGE);
        System.out.println("                                    ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⠀⠀⠀⠀⠀⠀");
        System.out.println("                                    ⠀⣄⠀⠀⠀⣦⡀⠀⢀⣧⡀⠀⠀⣼⣄⠀⠀⢀⣷⡀⠀⣰⣿⣇⠀⠀⣠⡎⠀");
        System.out.println("                                    ⠀⢿⣷⣄⠀⠻⣷⠀⣼⣿⣷⡀⠰⣿⣿⠆⢠⣾⣿⣷⠈⢻⡿⠋⣠⣾⣿⠁⠀");
        System.out.println("                                    ⠀⠀⠘⣿⣿⣷⣄⠀⢰⣿⣿⣿⣿⣄⠙⠏⢠⣿⣿⣿⣿⣧⠈⢀⣼⣿⣿⡟⠀⠀");
        System.out.println("                                    ⠀⠀⠀⠹⠿⣿⣿⣷⣾⣿⣿⣿⣿⣿⣦⣰⣿⣿⣿⣿⣿⣿⣷⣿⣿⡿⠛⠃⠀⠀");
        System.out.println("                                    ⠀⠀" + RED_BOLD + "⣶⡆" + ORANGE + "⢸⣿⣿⣿⣿⠿⠿⢿⣿⣿⣿⣿⣿⠿⠿⣿⣿⣿⣿⣿" + RED_BOLD + " ⢿⠇" + ORANGE);
        System.out.println("                                    ⠀⠀⠀⠈⢠⣾⣿⣿⣿⡇" + RED_BOLD +"⢰⣶ " + ORANGE + "⣿⣿⣿⣿⠁" + RED_BOLD+"⢰⣦" + ORANGE + "⠈⣿⣿⣿⣿⣷⡄⠀⠀⠀");
        System.out.println("                        ⠀           ⠀⠀⠀⠀⠙⠿⢿⣿⣷⣤⣀⣴⣿⣿⣿⣿⣧⣄⣠⣼⣿⣿⣿⠿⠛⠀⠀⠀⠀");
        System.out.println("                                    ⠀⠀⠀⠀⠀⠀⠀⠀⠉⠉⠉⠉⠛⠛⠛⠛⠛⠛⠉⠉⠉⠁⠀⠀⠀⠀⠀⠀⠀");
        System.out.println(GREEN);
        System.out.println("                  ▄▄    ▄▄     ██                                                          ▄▄");
        System.out.println("                  ▀██  ██▀     ▀▀                 ██                                       ██");
        System.out.println("                   ██  ██    ████      ▄█████▄  ███████    ▄████▄    ██▄████  ▀██  ███     ██");
        System.out.println("                   ██  ██      ██     ██▀    ▀    ██      ██▀  ▀██   ██▀       ██▄ ██      ██");
        System.out.println("                    ████       ██     ██          ██      ██    ██   ██         ████▀      ▀▀");
        System.out.println("                    ████    ▄▄▄██▄▄▄  ▀██▄▄▄▄█    ██▄▄▄   ▀██▄▄██▀   ██          ███       ▄▄");
        System.out.println("                    ▀▀▀▀    ▀▀▀▀▀▀▀▀    ▀▀▀▀▀      ▀▀▀▀     ▀▀▀▀     ▀▀          ██        ▀▀");
        System.out.println("                                                                               ███");
        System.out.print(RESET);
    }
}