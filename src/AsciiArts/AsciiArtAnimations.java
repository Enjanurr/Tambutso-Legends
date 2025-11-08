package AsciiArts;

public class AsciiArtAnimations {

    public void printTitleArt1() {
        System.out.println("                :::::::::::       :::         ::::    :::::    :::::::::     :::    :::    :::::::::::    ::::::::      :::::::: ");
        System.out.println("                    :+:         :+: :+:       +:+:+: :+:+:+    :+:    :+:    :+:    :+:        :+:       :+:    :+:    :+:    :+:");
        System.out.println("                    +:+        +:+   +:+      +:+ +:+:+ +:+    +:+    +:+    +:+    +:+        +:+       +:+           +:+    +:+");
        System.out.println("                    +#+       +#++:++#++:     +#+  +:+  +#+    +#++:++#+     +#+    +:+        +#+       +#++:++#++    +#+    +:+");
        System.out.println("                    +#+       +#+     +#+     +#+       +#+    +#+    +#+    +#+    +#+        +#+              +#+    +#+    +#+");
        System.out.println("                    #+#       #+#     #+#     #+#       #+#    #+#    #+#    #+#    #+#        #+#       #+#    #+#    #+#    #+#");
        System.out.println("                    ###       ###     ###     ###       ###    #########      ########         ###        ########      ######## ");
    }

    public void printTitleArt2() {
        System.out.println("                          :::           ::::::::::    ::::::::     ::::::::::    ::::    :::    :::::::::      :::::::: ");
        System.out.println("                          :+:           :+:          :+:    :+:    :+:           :+:+:   :+:    :+:    :+:    :+:    :+:");
        System.out.println("                          +:+           +:+          +:+           +:+           :+:+:+  +:+    +:+    +:+    +:+       ");
        System.out.println("                          +#+           +#++:++#     :#:           +#++:++#      +#+ +:+ +#+    +#+    +:+    +#++:++#++");
        System.out.println("                          +#+           +#+          +#+   +#+#    +#+           +#+  +#+#+#    +#+    +#+           +#+");
        System.out.println("                          #+#           #+#          #+#    #+#    #+#           #+#   #+#+#    #+#    #+#    #+#    #+#");
        System.out.println("                          ##########    ##########    ########     ##########    ###    ####    #########      ######## ");
    }

    // MAP 1 JEEPNEY
    public void runMap1JeepneyAnimation() throws InterruptedException {
        final String CURSOR_UP_11 = "\u001b[11A";
        final int DELAY_MS = 500;
        printJeepFrame1();
        Thread.sleep(DELAY_MS);

        System.out.print(CURSOR_UP_11);

        printJeepFrame2();
        Thread.sleep(DELAY_MS);

        System.out.print(CURSOR_UP_11);

        printJeepFrame3();
        Thread.sleep(DELAY_MS);
    }

    private void printJeepFrame1() {
        System.out.println("____________________________________________________|||          |                                                            ");
        System.out.println("====================================================|||          |                                                            ");
        System.out.println(" |=====\\ | ,~~. |       |       |       | ,~~. |     \\           |                                                            ");
        System.out.println(" |______\\| |  | |_______|_______|_______| |  | |   ,~``~.        |                                                            ");
        System.out.println("  _|, .    '..' '-----------------------' '..' |  /  ,.  \\-------|-,                                                           ");
        System.out.println(" / |          {NAGA           MINGLANILLA}      \\_\\  `'  /. _____|_\\_  |                                                        ");
        System.out.println(" {||                                                              `;_\\ |                                                        ");
        System.out.println(" {|]          ,~``~.                                        ,~``~.=====|                                                        ");
        System.out.println(" [_[__(______/  ,.  \\__)================================== /  ,.  \\----|                                                        ");
        System.out.println(" [_______/   \\  `'  /                                      \\  `'  /                                                            ");
        System.out.println("              \"~..~\"                                        `~..~\"                                                             ");
    }

    private void printJeepFrame2() {
        System.out.println("                        ____________________________________________________|||          |                                            ");
        System.out.println("                        ====================================================|||          |                                            ");
        System.out.println("                         |=====\\ | ,~~. |       |       |       | ,~~. |     \\           |                                            ");
        System.out.println("                         |______\\| |  | |_______|_______|_______| |  | |   ,~``~.        |                                            ");
        System.out.println("                          _|, .    '..' '-----------------------' '..' |  /  ,.  \\-------|-,                                           ");
        System.out.println("                         / |          {NAGA           MINGLANILLA}      \\_\\  `'  /. _____|_\\_  |                                        ");
        System.out.println("                         {||                                                              `;_\\ |                                        ");
        System.out.println("                         {|]          ,~``~.                                        ,~``~.=====|                                        ");
        System.out.println("                         [_[__(______/  ,.  \\__)================================== /  ,.  \\----|                                        ");
        System.out.println("                         [_______/   \\  `'  /                                      \\  `'  /                                            ");
        System.out.println("                                      \"~..~\"                                        `~..~\"                                             ");
    }

    private void printJeepFrame3() {
        System.out.println("                                        ____________________________________________________|||          |                                        ");
        System.out.println("                                        ====================================================|||          |                                        ");
        System.out.println("                                         |=====\\ | ,~~. |       |       |       | ,~~. |     \\           |                                        ");
        System.out.println("                                         |______\\| |  | |_______|_______|_______| |  | |   ,~``~.        |                                        ");
        System.out.println("                                          _|, .    '..' '-----------------------' '..' |  /  ,.  \\-------|-,                                       ");
        System.out.println("                                         / |          {NAGA           MINGLANILLA}      \\_\\  `'  /. _____|_\\_  |                                    ");
        System.out.println("                                         {||                                                              `;_\\ |                                    ");
        System.out.println("                                         {|]          ,~``~.                                        ,~``~.=====|                                    ");
        System.out.println("                                         [_[__(______/  ,.  \\__)================================== /  ,.  \\----|                                    ");
        System.out.println("                                         [_______/   \\  `'  /                                      \\  `'  /                                        ");
        System.out.println("                                                      \"~..~\"                                        `~..~\"                                         ");
    }

    // MAP 2 JEEPNEY
    public void runMap2JeepneyAnimation() throws InterruptedException {
        final String CURSOR_UP_11 = "\u001b[11A";
        final int DELAY_MS = 500;
        printMap2JeepFrame1();
        Thread.sleep(DELAY_MS);

        System.out.print(CURSOR_UP_11);

        printMap2JeepFrame2();
        Thread.sleep(DELAY_MS);

        System.out.print(CURSOR_UP_11);

        printMap2JeepFrame3();
        Thread.sleep(DELAY_MS);
    }

    private void printMap2JeepFrame1() {
        System.out.println("____________________________________________________|||          |                                                            ");
        System.out.println("====================================================|||          |                                                            ");
        System.out.println(" |=====\\ | ,~~. |       |       |       | ,~~. |     \\           |                                                            ");
        System.out.println(" |______\\| |  | |_______|_______|_______| |  | |   ,~``~.        |                                                            ");
        System.out.println("  _|, .    '..' '-----------------------' '..' |  /  ,.  \\-------|-,                                                           ");
        System.out.println(" / |          {MINGLANILLA         CIT-U}      \\_\\  `'  /. _____|_\\_  |                                                        ");
        System.out.println(" {||                                                              `;_\\ |                                                        ");
        System.out.println(" {|]          ,~``~.                                        ,~``~.=====|                                                        ");
        System.out.println(" [_[__(______/  ,.  \\__)================================== /  ,.  \\----|                                                        ");
        System.out.println(" [_______/   \\  `'  /                                      \\  `'  /                                                            ");
        System.out.println("              \"~..~\"                                        `~..~\"                                                             ");
    }

    private void printMap2JeepFrame2() {
        System.out.println("                        ____________________________________________________|||          |                                            ");
        System.out.println("                        ====================================================|||          |                                            ");
        System.out.println("                         |=====\\ | ,~~. |       |       |       | ,~~. |     \\           |                                            ");
        System.out.println("                         |______\\| |  | |_______|_______|_______| |  | |   ,~``~.        |                                            ");
        System.out.println("                          _|, .    '..' '-----------------------' '..' |  /  ,.  \\-------|-,                                           ");
        System.out.println("                         / |          {MINGLANILLA         CIT-U}      \\_\\  `'  /. _____|_\\_  |                                        ");
        System.out.println("                         {||                                                              `;_\\ |                                        ");
        System.out.println("                         {|]          ,~``~.                                        ,~``~.=====|                                        ");
        System.out.println("                         [_[__(______/  ,.  \\__)================================== /  ,.  \\----|                                        ");
        System.out.println("                         [_______/   \\  `'  /                                      \\  `'  /                                            ");
        System.out.println("                                      \"~..~\"                                        `~..~\"                                             ");
    }

    private void printMap2JeepFrame3() {
        System.out.println("                                        ____________________________________________________|||          |                                        ");
        System.out.println("                                        ====================================================|||          |                                        ");
        System.out.println("                                         |=====\\ | ,~~. |       |       |       | ,~~. |     \\           |                                        ");
        System.out.println("                                         |______\\| |  | |_______|_______|_______| |  | |   ,~``~.        |                                        ");
        System.out.println("                                          _|, .    '..' '-----------------------' '..' |  /  ,.  \\-------|-,                                       ");
        System.out.println("                                         / |          {MINGLANILLA         CIT-U}      \\_\\  `'  /. _____|_\\_  |                                    ");
        System.out.println("                                         {||                                                              `;_\\ |                                    ");
        System.out.println("                                         {|]          ,~``~.                                        ,~``~.=====|                                    ");
        System.out.println("                                         [_[__(______/  ,.  \\__)================================== /  ,.  \\----|                                    ");
        System.out.println("                                         [_______/   \\  `'  /                                      \\  `'  /                                        ");
        System.out.println("                                                      \"~..~\"                                        `~..~\"                                         ");
    }

    // MAP 3 JEEPNEY
    public void runMap3JeepneyAnimation() throws InterruptedException {
        final String CURSOR_UP_11 = "\u001b[11A";
        final int DELAY_MS = 500;
        printMap3JeepFrame1();
        Thread.sleep(DELAY_MS);

        System.out.print(CURSOR_UP_11);

        printMap3JeepFrame2();
        Thread.sleep(DELAY_MS);

        System.out.print(CURSOR_UP_11);

        printMap3JeepFrame3();
        Thread.sleep(DELAY_MS);
    }

    private void printMap3JeepFrame1() {
        System.out.println("____________________________________________________|||          |                                                            ");
        System.out.println("====================================================|||          |                                                            ");
        System.out.println(" |=====\\ | ,~~. |       |       |       | ,~~. |     \\           |                                                            ");
        System.out.println(" |______\\| |  | |_______|_______|_______| |  | |   ,~``~.        |                                                            ");
        System.out.println("  _|, .    '..' '-----------------------' '..' |  /  ,.  \\-------|-,                                                           ");
        System.out.println(" / |          {CIT-U             IT PARK}      \\_\\  `'  /. _____|_\\_  |                                                        ");
        System.out.println(" {||                                                              `;_\\ |                                                        ");
        System.out.println(" {|]          ,~``~.                                        ,~``~.=====|                                                        ");
        System.out.println(" [_[__(______/  ,.  \\__)================================== /  ,.  \\----|                                                        ");
        System.out.println(" [_______/   \\  `'  /                                      \\  `'  /                                                            ");
        System.out.println("              \"~..~\"                                        `~..~\"                                                             ");
    }

    private void printMap3JeepFrame2() {
        System.out.println("                        ____________________________________________________|||          |                                            ");
        System.out.println("                        ====================================================|||          |                                            ");
        System.out.println("                         |=====\\ | ,~~. |       |       |       | ,~~. |     \\           |                                            ");
        System.out.println("                         |______\\| |  | |_______|_______|_______| |  | |   ,~``~.        |                                            ");
        System.out.println("                          _|, .    '..' '-----------------------' '..' |  /  ,.  \\-------|-,                                           ");
        System.out.println("                         / |          {CIT-U             IT PARK}      \\_\\  `'  /. _____|_\\_  |                                        ");
        System.out.println("                         {||                                                              `;_\\ |                                        ");
        System.out.println("                         {|]          ,~``~.                                        ,~``~.=====|                                        ");
        System.out.println("                         [_[__(______/  ,.  \\__)================================== /  ,.  \\----|                                        ");
        System.out.println("                         [_______/   \\  `'  /                                      \\  `'  /                                            ");
        System.out.println("                                      \"~..~\"                                        `~..~\"                                             ");
    }

    private void printMap3JeepFrame3() {
        System.out.println("                                        ____________________________________________________|||          |                                        ");
        System.out.println("                                        ====================================================|||          |                                        ");
        System.out.println("                                         |=====\\ | ,~~. |       |       |       | ,~~. |     \\           |                                        ");
        System.out.println("                                         |______\\| |  | |_______|_______|_______| |  | |   ,~``~.        |                                        ");
        System.out.println("                                          _|, .    '..' '-----------------------' '..' |  /  ,.  \\-------|-,                                       ");
        System.out.println("                                         / |          {CIT-U             IT PARK}      \\_\\  `'  /. _____|_\\_  |                                    ");
        System.out.println("                                         {||                                                              `;_\\ |                                    ");
        System.out.println("                                         {|]          ,~``~.                                        ,~``~.=====|                                    ");
        System.out.println("                                         [_[__(______/  ,.  \\__)================================== /  ,.  \\----|                                    ");
        System.out.println("                                         [_______/   \\  `'  /                                      \\  `'  /                                        ");
        System.out.println("                                                      \"~..~\"                                        `~..~\"                                         ");
    }

    // LOADING ART (BEFORE EVERY MAP)
    public void runSunriseAnimation() throws InterruptedException {
        final String CURSOR_UP_11 = "\u001b[11A";
        final int DELAY_MS = 1000;

        printSunriseFrame1();
        Thread.sleep(DELAY_MS);

        System.out.print(CURSOR_UP_11);

        printSunriseFrame2();
        Thread.sleep(DELAY_MS);

        System.out.print(CURSOR_UP_11);

        printSunriseFrame3();
        Thread.sleep(DELAY_MS);
    }

    private void printSunriseFrame1() {
        System.out.println("                                                                                                                    ");
        System.out.println("                                                 ^^                                                                 ");
        System.out.println("                                             ^^       ^^                                                            ");
        System.out.println("                                                                             @@@@@@@            ^^                  ");
        System.out.println("                                                                          @@@@@@@@@@@@@                             ");
        System.out.println("                                            ~~~~ ~~ ~~~~~ ~~~~~~~~ ~~ ~~~ &&&&&&&&&&&&& ~~  ~~~~~~~ ~~~~~~~~~~~ ~~~ ");
        System.out.println("                                            ~         ~~   ~ ~   ~~  ~~   ~~ ~~~~~~~~ ~    ~ ~  ~~ ~       ~~     ~ ");
        System.out.println("                                              ~      ~~      ~~ ~~ ~~   ~~   ~~~ ~~~     ~     ~~~    ~ ~~~  ~ ~~   ");
        System.out.println("                                              ~  ~~     ~         ~~  ~~   ~         ~ ~       ~~ ~ ~~  ~~ ~        ");
        System.out.println("                                            ~  ~       ~ ~      ~           ~~ ~~ ~~  ~      ~~  ~             ~~   ");
        System.out.println("                                                  ~             ~        ~      ~      ~~   ~             ~         ");
    }

    private void printSunriseFrame2() {
        System.out.println("                                                                                                                    ");
        System.out.println("                                                      ^^                                                            ");
        System.out.println("                                                  ^^       ^^                @@@@@@@                                ");
        System.out.println("                                                                          @@@@@@@@@@@@@              ^^             ");
        System.out.println("                                                                        @@@@@@@@@@@@@@@@@                           ");
        System.out.println("                                            ~~~~ ~~ ~~~~~ ~~~~~~~~ ~~~~ &&&&&&&&&&&&&&&&& ~~~~~~~ ~~~~~~~~~~~ ~~~   ");
        System.out.println("                                            ~         ~~   ~  ~         ~~~~~~~~~~~~~~~~~  ~       ~~     ~~ ~      ");
        System.out.println("                                              ~      ~~      ~~ ~~ ~~     ~~~~~~~~~~~ ~~  ~     ~~~    ~ ~~~  ~     ");
        System.out.println("                                             ~  ~~     ~         ~           ~~~  ~~~       ~~ ~ ~~  ~~ ~           ");
        System.out.println("                                             ~  ~       ~ ~      ~  ~       ~      ~    ~  ~      ~~  ~             ");
        System.out.println("                                               ~             ~        ~      ~      ~~   ~             ~            ");
    }

    private void printSunriseFrame3() {
        System.out.println("                                                                             @@@@@@@                                ");
        System.out.println("                                                            ^^            @@@@@@@@@@@@@                             ");
        System.out.println("                                                        ^^      ^^      @@@@@@@@@@@@@@@@@                           ");
        System.out.println("                                                                       @@@@@@@@@@@@@@@@@@@                ^^        ");
        System.out.println("                                                                      @@@@@@@@@@@@@@@@@@@@@                         ");
        System.out.println("                                            ~~~~ ~~ ~~~~~ ~~~~~~~~ ~~ &&&&&&&&&&&&&&&&&&&&& ~~~~~~~ ~~~~~~~~~~~ ~~~ ");
        System.out.println("                                            ~         ~~   ~  ~       ~~~~~~~~~~~~~~~~~~~~ ~       ~~     ~~ ~      ");
        System.out.println("                                              ~      ~~      ~~ ~~ ~~  ~~~~~~~~~~~~~ ~~~~  ~     ~~~    ~ ~~~  ~ ~~ ");
        System.out.println("                                              ~  ~~     ~         ~      ~~~~~~  ~~ ~~~       ~~ ~ ~~  ~~ ~         ");
        System.out.println("                                            ~  ~       ~ ~      ~           ~~ ~~~~~~  ~      ~~  ~             ~~  ");
        System.out.println("                                               ~             ~        ~      ~      ~~   ~             ~            ");
    }

    // LOADING ART (AFTER EVERY MAP)
    public void runSunsetAnimation() throws InterruptedException {
        final String CURSOR_UP_11 = "\u001b[11A";
        final int DELAY_MS = 1000;

        printSunsetFrame1();
        Thread.sleep(DELAY_MS);

        System.out.print(CURSOR_UP_11);

        printSunsetFrame2();
        Thread.sleep(DELAY_MS);

        System.out.print(CURSOR_UP_11);

        printSunsetFrame3();
        Thread.sleep(DELAY_MS);
    }

    private void printSunsetFrame1() {
        System.out.println("                                                                             @@@@@@@                                            ");
        System.out.println("                                                            ^^            @@@@@@@@@@@@@                                               ");
        System.out.println("                                                        ^^      ^^      @@@@@@@@@@@@@@@@@                                            ");
        System.out.println("                                                                       @@@@@@@@@@@@@@@@@@@                     ^^                   ");
        System.out.println("                                                                      @@@@@@@@@@@@@@@@@@@@@                                         ");
        System.out.println("                                            ~~~~ ~~ ~~~~~ ~~~~~~~~ ~~ &&&&&&&&&&&&&&&&&&&&& ~~~~~~~ ~~~~~~~~~~~ ~~~         ");
        System.out.println("                                            ~         ~~   ~  ~       ~~~~~~~~~~~~~~~~~~~~ ~       ~~     ~~ ~              ");
        System.out.println("                                              ~      ~~      ~~ ~~ ~~  ~~~~~~~~~~~~~ ~~~~  ~     ~~~    ~ ~~~  ~ ~~         ");
        System.out.println("                                              ~  ~~     ~         ~      ~~~~~~  ~~ ~~~       ~~ ~ ~~  ~~ ~                 ");
        System.out.println("                                            ~  ~       ~ ~      ~           ~~ ~~~~~~  ~      ~~  ~             ~~          ");
        System.out.println("                                               ~             ~        ~      ~      ~~   ~             ~                    ");
    }

    private void printSunsetFrame2() {
        System.out.println("                                                                                                                                ");
        System.out.println("                                                      ^^                                                                             ");
        System.out.println("                                                  ^^       ^^                @@@@@@@                                                        ");
        System.out.println("                                                                          @@@@@@@@@@@@@              ^^                                 ");
        System.out.println("                                                                        @@@@@@@@@@@@@@@@@                                               ");
        System.out.println("                                            ~~~~ ~~ ~~~~~ ~~~~~~~~ ~~~~ &&&&&&&&&&&&&&&&& ~~~~~~~ ~~~~~~~~~~~ ~~~~~          ");
        System.out.println("                                            ~         ~~   ~  ~         ~~~~~~~~~~~~~~~~~  ~       ~~     ~~ ~              ");
        System.out.println("                                              ~      ~~      ~~ ~~ ~~     ~~~~~~~~~~~ ~~  ~     ~~~    ~ ~~~  ~             ");
        System.out.println("                                             ~  ~~     ~         ~           ~~~  ~~~       ~~ ~ ~~  ~~ ~                   ");
        System.out.println("                                             ~  ~       ~ ~      ~  ~       ~      ~    ~  ~      ~~  ~                     ");
        System.out.println("                                               ~             ~        ~      ~      ~~   ~             ~                    ");
    }

    private void printSunsetFrame3() {
        System.out.println("                                                                                                                                        ");
        System.out.println("                                                 ^^                                                                                     ");
        System.out.println("                                             ^^       ^^                                                                                ");
        System.out.println("                                                                             @@@@@@@            ^^                                      ");
        System.out.println("                                                                          @@@@@@@@@@@@@                                                 ");
        System.out.println("                                            ~~~~ ~~ ~~~~~ ~~~~~~~~ ~~ ~~~ &&&&&&&&&&&&& ~~  ~~~~~~~ ~~~~~~~~~~~ ~~~         ");
        System.out.println("                                            ~         ~~   ~ ~   ~~  ~~   ~~ ~~~~~~~~ ~    ~ ~  ~~ ~       ~~     ~         ");
        System.out.println("                                              ~      ~~      ~~ ~~ ~~   ~~   ~~~ ~~~     ~     ~~~    ~ ~~~  ~ ~~           ");
        System.out.println("                                              ~  ~~     ~         ~~  ~~   ~         ~ ~       ~~ ~ ~~  ~~ ~                ");
        System.out.println("                                            ~  ~       ~ ~      ~           ~~ ~~ ~~  ~      ~~  ~             ~~           ");
        System.out.println("                                                  ~             ~        ~      ~      ~~   ~             ~                 ");
    }

    public void runEncounterDialogueAnimation() throws InterruptedException {
        final String CURSOR_UP_3 = "\u001b[3A";
        final int DELAY_MS = 1000;

        printEncounterDialogueFrame1();
        Thread.sleep(DELAY_MS);

        System.out.print(CURSOR_UP_3);

        printEncounterDialogueFrame2();
        Thread.sleep(DELAY_MS);

        System.out.print(CURSOR_UP_3);

        printEncounterDialogueFrame3();
        Thread.sleep(DELAY_MS);
    }

    // anim before boss encounter
    private void printEncounterDialogueFrame1() {
        System.out.println("                                    .-. . .   .-. . . .-. .  . . .   .-. .-. .-. .-. .-. .-. .-. . . .-. .-.      ");
        System.out.println("                                    |-| |\\|   |-  |\\| |-  |\\/|  |    |-| |-' |-' |(  | | |-| |   |-| |-  `-.      ");
        System.out.println("                                    ` ' ' `   `-' ' ` `-' '  `  `    ` ' '   '   ' ' `-' ` ' `-' ' ` `-' `-' . ");
    }

    private void printEncounterDialogueFrame2() {
        System.out.println("                                    .-. . .   .-. . . .-. .  . . .   .-. .-. .-. .-. .-. .-. .-. . . .-. .-.      ");
        System.out.println("                                    |-| |\\|   |-  |\\| |-  |\\/|  |    |-| |-' |-' |(  | | |-| |   |-| |-  `-.      ");
        System.out.println("                                    ` ' ' `   `-' ' ` `-' '  `  `    ` ' '   '   ' ' `-' ` ' `-' ' ` `-' `-' . .");
    }

    private void printEncounterDialogueFrame3() {
        System.out.println("                                    .-. . .   .-. . . .-. .  . . .   .-. .-. .-. .-. .-. .-. .-. . . .-. .-.     ");
        System.out.println("                                    |-| |\\|   |-  |\\| |-  |\\/|  |    |-| |-' |-' |(  | | |-| |   |-| |-  `-.      ");
        System.out.println("                                    ` ' ' `   `-' ' ` `-' '  `  `    ` ' '   '   ' ' `-' ` ' `-' ' ` `-' `-' . . .");
    }

    // after previous anim
    public void runBossEncounterArt() {
        System.out.println("                                                                 _____________________________");
        System.out.println("                                                                 |             ,---.         |");
        System.out.println("                                                                 |            |   |          |");
        System.out.println("                                                                 |            |   |          |");
        System.out.println("                                                                 |            |   |          |");
        System.out.println("                                                                 |            |   |          |");
        System.out.println("                                                                 |            |  .'          |");
        System.out.println("                                                                 |            `--'           |");
        System.out.println("                                                                 |            .--.           |");
        System.out.println("                                                                 |            '--            |");
        System.out.println("                                                                 __________         __________");
        System.out.println("                                                                          |       /");
        System.out.println("                                                                          |      /");
        System.out.println("                                                                          |     /");
        System.out.println("                                                                          |    /");
        System.out.println("                                                                          |   /");
        System.out.println("                                                                          |  /");
        System.out.println("                                                                          | /");
        System.out.println("                                                                          --");
    }

// BOSS TITLE ARTS

    public void printVaughtTitleArt() {
        System.out.println("                                                .s    s.                                                     ");
        System.out.println("                                                      SS. .s5SSSs.  .s    s.  .s5SSSs.  .s    s.  .s    s.   ");
        System.out.println("                                                sS    S%S       SS.       SS.       SS.       SS.       SS.  ");
        System.out.println("                                                SS    S%S sS    S%S sS    S%S sS    `:; sS    S%S sSs.  S%S  ");
        System.out.println("                                                SS    S%S SSSs. S%S SS    S%S SS        SSSs. S%S SS `S.S%S  ");
        System.out.println("                                                 SS   S%S SS    S%S SS    S%S SS        SS    S%S SS  `sS%S  ");
        System.out.println("                                                 SS   `:; SS    `:; SS    `:; SS   ``:; SS    `:; SS    `:;  ");
        System.out.println("                                                  SS  ;,. SS    ;,. SS    ;,. SS    ;,. SS    ;,. SS    ;,.  ");
        System.out.println("                                                   `:;;:' :;    ;:' `:;;;;;:' `:;;;;;:' :;    ;:' :;    ;:'  ");
        System.out.println();
        System.out.println("                                                ___       ___     __   __        __      __                     ");
        System.out.println("                                                 |  |__| |__     |__) /  \\  /\\  |  \\    |__) |  | |    |    \\ / ");
        System.out.println("                                                 |  |  | |___    |  \\ \\__/ /~~\\ |__/    |__) \\__/ |___ |___  |  ");
    }

    public void printAdrianTitleArt() {
        System.out.println("                                                   .s5SSSs.                                              ");
        System.out.println("                                                         SS. .s5SSSs.  .s5SSSs.  s.  .s5SSSs.  .s    s.  ");
        System.out.println("                                                   sS    S%S       SS.       SS. SS.       SS.       SS. ");
        System.out.println("                                                   SS    S%S sS    S%S sS    S%S S%S sS    S%S sSs.  S%S ");
        System.out.println("                                                   SSSs. S%S SS    S%S SS .sS;:' S%S SSSs. S%S SS `S.S%S ");
        System.out.println("                                                   SS    S%S SS    S%S SS    ;,  S%S SS    S%S SS  `sS%S ");
        System.out.println("                                                   SS    `:; SS    `:; SS    `:; `:; SS    `:; SS    `:; ");
        System.out.println("                                                   SS    ;,. SS    ;,. SS    ;,. ;,. SS    ;,. SS    ;,. ");
        System.out.println("                                                   :;    ;:' ;;;;;;;:' `:    ;:' ;:' :;    ;:' :;    ;:' ");
        System.out.println();
        System.out.println("                                    ___       ___     __   ___  __             ___  __   __      __        __   ___  __  ");
        System.out.println("                                     |  |__| |__     |__) |__  /  ` |__/ |    |__  /__` /__`    |__)  /\\  /  ` |__  |__) ");
        System.out.println("                                     |  |  | |___    |  \\ |___ \\__, |  \\ |___ |___ .__/ .__/    |  \\ /~~\\ \\__, |___ |  \\ ");
    }

    public void printSirKhaiTitleArt() {
        System.out.println("                                                .s5SSSs.                    .s    s.                          ");
        System.out.println("                                                      SS. s.  .s5SSSs.            SS. .s    s.  .s5SSSs.  s.  ");
        System.out.println("                                                sS    `:; SS.       SS.     sS    S%S       SS.       SS. SS. ");
        System.out.println("                                                SS        S%S sS    S%S     SS    S%S sS    S%S sS    S%S S%S ");
        System.out.println("                                                `:;;;;.   S%S SS .sS;:'     SSSSs.S:' SSSs. S%S SSSs. S%S S%S ");
        System.out.println("                                                      ;;. S%S SS    ;,      SS  'SS.  SS    S%S SS    S%S S%S ");
        System.out.println("                                                      `:; `:; SS    `:;     SS    `:; SS    `:; SS    `:; `:; ");
        System.out.println("                                                .,;   ;,. ;,. SS    ;,.     SS    ;,. SS    ;,. SS    ;,. ;,. ");
        System.out.println("                                                `:;;;;;:' ;:' `:    ;:'     :;    ;:' :;    ;:' :;    ;:' ;:' ");
        System.out.println();
        System.out.println("                                           ___       ___                 __      __   ___     __   __        __   __  ");
        System.out.println("                                            |  |__| |__     |__/ | |\\ | / _`    /  \\ |__     |__) /  \\  /\\  |  \\ /__` ");
        System.out.println("                                            |  |  | |___    |  \\ | | \\| \\__>    \\__/ |       |  \\ \\__/ /~~\\ |__/ .__/ ");
    }

    public void printJolliKhaiTitleArt() {
        System.out.println("                                           @@@   @@@@@@   @@@       @@@       @@@  @@@  @@@  @@@  @@@   @@@@@@   @@@  ");
        System.out.println("                                           @@@  @@@@@@@@  @@@       @@@       @@@  @@@  @@@  @@@  @@@  @@@@@@@@  @@@  ");
        System.out.println("                                           @@!  @@!  @@@  @@!       @@!       @@!  @@!  !@@  @@!  @@@  @@!  @@@  @@!  ");
        System.out.println("                                           !@!  !@!  @!@  !@!       !@!       !@!  !@!  @!!  !@!  @!@  !@!  @!@  !@!  ");
        System.out.println("                                           !!@  @!@  !@!  @!!       @!!       !!@  @!@@!@!   @!@!@!@!  @!@!@!@!  !!@  ");
        System.out.println("                                           !!!  !@!  !!!  !!!       !!!       !!!  !!@!!!    !!!@!!!!  !!!@!!!!  !!!  ");
        System.out.println("                                           !!:  !!:  !!!  !!:       !!:       !!:  !!: :!!   !!:  !!!  !!:  !!!  !!:  ");
        System.out.println("                                      !!:  :!:  :!:  !:!   :!:       :!:      :!:  :!:  !:!  :!:  !:!  :!:  !:!  :!:  ");
        System.out.println("                                      ::: : ::  ::::: ::   :: ::::   :: ::::   ::   ::  :::  ::   :::  ::   :::   ::  ");
        System.out.println("                                       : :::     : :  :   : :: : :  : :: : :  :     :   :::   :   : :   :   : :  :    ");
        System.out.println();
        System.out.println("                               _____ __  __ _____    ____  _____ _____         ____  _____ __ __ _____  ____  __ __  __  ____  ");
        System.out.println("                                ||   ||==|| ||==     ||=)  ||==  ||==   ====  (( ___ ||_// || || ||  ) (( ___ || ||\\|| (( ___ ");
        System.out.println("                                ||   ||  || ||___    ||_)) ||___ ||___         \\\\_|| || \\\\ \\\\_// ||_//  \\\\_|| || || \\||  \\\\_|| ");
    }
}