# Aufbau der `README.md`-Datei (PiS, SoSe 2021)

<!-- TOC depthfrom:2 -->

- [Einleitung](#einleitung)
- [Das Template](#das-template)
- [Hinweise](#hinweise)
    - [Zum Aufbau](#zum-aufbau)
    - [Zur Dateiübersicht](#zur-datei%C3%BCbersicht)
    - [Zur Übersicht der Lines of Code LoC](#zur-%C3%BCbersicht-der-lines-of-code-loc)
    - [Quellenangaben](#quellenangaben)
- [Tipps und Hinweise](#tipps-und-hinweise)

<!-- /TOC -->

## Einleitung

Die `README.md`-Datei dient der Dokumentation Ihres Projekts. Halten Sie sich bitte an die hier beschriebenen Vorgaben. Bitte erstellen Sie nur _eine_ Datei namens `README.md`, in der sich die gesamte Dokumentation befindet. Teilen Sie die Dokumentation nicht auf mehrere Dateien auf. Bilder und sonstige Dateien, die Sie einbinden, belassen Sie bitte auf der gleichen Verzeichnisebene wie das `README.md`.

> Die Dokumentation ist verpflichtend in Markdown zu schreiben. [Markdown](https://de.wikipedia.org/wiki/Markdown) ist ein sehr einfaches Auszeichnungsformat, mit dem man gewöhnlichen Text (_plain text_) um Formatierungshinweise anreichert. Mit einem Markdown-Compiler kann man das Textdokument in ein HTML-Dokument übersetzen und anzeigen lassen. Viele Editoren wie z.B. Visual Studio Code liefern von Haus aus eine Markdown-Unterstützung mit Voransicht des übersetzten Ergebnisses.

Wenn Ihnen der Unterschied zwischen Markdown und der aufbereiteten Ansicht eines Markdown-Dokuments unklar ist, schauen Sie sich das Beispiel an:

* Das ist der [Bitboard-Text](https://raw.githubusercontent.com/denkspuren/BitboardC4/master/BitboardDesign.md), so wie ich ihn in Markdown geschrieben habe.
* Und so sieht es aus, wenn der [Text gerendert](https://github.com/denkspuren/BitboardC4/blob/master/BitboardDesign.md) wird; in dem Fall macht das die GitHub-Versionsverwaltung automatisch.

Damit kein Missverständnis aufkommt: Sie liefern die Markdown-Datei ab (darum lautet die Dateiendung `.md`) und kein HTML-Dokument. Bitte prüfen Sie vor der Abgabe die Voransicht z.B. mit Visual Studio Code, ob das generierte Layout "anständig" aussieht und alle beabsichtigten Formatierungen umsetzt. Eine "zerschossene" Darstellung lehnen wir gegebenenfalls ab.

## Das Template

Das `README.md` folgt im Aufbau dem Abschnitt "Dokumentation (DOK)" im Dokument zu den [Prüfungskriterien PiS-Projekt](https://docs.google.com/document/d/1Y5RQPpVdyr1KyEcDFpu22jX5Xb7h-__BjQO-Mj0X0ro/edit?usp=sharing). Der Textinhalt Ihrer Datei `README.md` sieht wie folgt aus; ersetzen Sie die beispielhaften Textstellen entsprechend:

~~~
# Bingo PvE (PiS, SoSe 2021) 

Autor: Julian Scheffler, 5164983

## Kurzbeschreibung (50-150 Wörter)

Mein Programm ist ein Bingo-Spiel, indem der Spieler gegen den Computer Bigno spielt.
Bingo ist ein Glücksspiel und ist als Lotteriespiel in einigen Ländern sehr beliebt. Außerdem ist Bingo ein Gesellschaftsspiel und wird gemeinsam gespielt
Die Idee ist die:
Man kauft sich eine oder mehrere Bingo Karten.
Eine Karte besteht aus 25 Zahlen zwischen 1 und 75.
Vor jeder Runde befinden sich 75 Kugeln von 1 bis 75 in einer Trommel.
Ein Moderator zieht eine Kugel und

Beschreiben Sie kurz und knapp, aber in vollständigen Sätzen, z.B. die Spielregeln zu dem von Ihnen umgesetzten Spiel. Bei einer Animation oder Simulation schreiben Sie, worum es geht. Geben Sie am Schluss in Klammern die Anzahl der Wörter an. (39 Wörter)

## Screenshot

![Screenshot](Screenshot.png)

## Bedienungshinweise

Beschreiben Sie die Bedienung Ihres Programms.

Wir gehen davon aus, dass wir Ihr Programm mit `gradle run` starten und mit `gradle test` die Testfälle ausführen können.

Wenn Sie statt `gradle` die `JShell` verwenden, dann gehört zu den Bedienungshinweisen dazu, wie wir Ihr Programm starten und wie wir die Testfälle ablaufen lassen können.

## Dateiübersicht und Lines of Code

    \build.gradle
    \README.md
    \bin\main\public\index.html
    \bin\main\TicTacToe\App.kt
    \bin\main\TicTacToe\T3.kt
    \src\main\kotlin\TicTacToe\App.kt
    \src\main\kotlin\TicTacToe\T3.kt
    \src\main\resources\public\index.html

    -------------------------------------------------------------------------------
    Language                     files          blank        comment           code
    -------------------------------------------------------------------------------
    Markdown                         1             71              0            270
    Kotlin                           3             27              3            113
    HTML                             1             11             17             80
    XML                              2              0              0             41
    Gradle                           1              8             12             16
    INI                              1              0              0             13
    -------------------------------------------------------------------------------
    SUM:                             9            117             32            533
    -------------------------------------------------------------------------------

## Verwendete Quellen

* Führen Sie als Punktliste die verwendeten Quellen auf
* Vergessen Sie bei Internetquellen nicht die Angabe des Zugriffsdatums: https://www.heise.de/newsticker/ (Abruf, 16.6.2021)
~~~

## Hinweise

### Zum Aufbau

* Geben Sie bitte Ihren Namen _und_ Ihre Matrikelnummer an. Ohne Matrikelnummer haben wir es unnötig schwer, Ihr Prüfungsergebnis zu melden.
* Fügen Sie nur _einen_(!) Screenshot Ihrer Anwendung in Aktion ein. Bitte denken Sie daran, einen relativen Link wie gezeigt zu der Bilddatei zu verwenden!  
* Ihre Anwendung sollte so selbstverständlich nutzbar sein, dass es eigentlich keiner Anleitung bedarf. Geben Sie sich bitte dennoch die Mühe, eine kurze Nutzungsanleitung zur Bedienung des Spiels zu schreiben. Dazu gehört nicht das Starten der Anwendung! Es geht um die Bedienung der Oberfläche.

### Zur Dateiübersicht

Die Dateiübersicht erstellen Sie bitte automatisch und fügen sie entsprechend dem obigen Beispiel eingerückt ein. Die Einrückung sorgt dafür, dass der Text wie ein Codeblock behandelt und hervorgehoben wird.

Wechseln Sie unter Windows mit `cd` in das Projektverzeichnis Ihrer Anwendung; im folgenden Beispiel heißt das Verzeichnis `TicTacToe`. Listen Sie alle Dateien mit `dir /S /B /A-D .` auf:

~~~
> dir /S /B /A-D .
C:\Users\Dominikus\GitTHM\JbX\TicTacToe\build.gradle
C:\Users\Dominikus\GitTHM\JbX\TicTacToe\README.md
C:\Users\Dominikus\GitTHM\JbX\TicTacToe\bin\main\public\index.html
C:\Users\Dominikus\GitTHM\JbX\TicTacToe\bin\main\TicTacToe\App.kt
C:\Users\Dominikus\GitTHM\JbX\TicTacToe\bin\main\TicTacToe\T3.kt
C:\Users\Dominikus\GitTHM\JbX\TicTacToe\src\main\kotlin\TicTacToe\App.kt
C:\Users\Dominikus\GitTHM\JbX\TicTacToe\src\main\kotlin\TicTacToe\T3.kt
C:\Users\Dominikus\GitTHM\JbX\TicTacToe\src\main\resources\public\index.html
~~~

Wenn Sie Linux oder MacOS verwenden, werden Sie die entsprechenden Befehle für die Kommandozeile sicher leicht herausfinden; es wird etwas in der Art von `ls -dl ./**/*` sein.

Entfernen Sie aus der Auflistung gegebenenfalls händisch die Angabe des Pfadkopfs bis einschließlich des Projektnamens. Das ist übersichtlicher:

    \build.gradle
    \README.md
    \bin\main\public\index.html
    \bin\main\TicTacToe\App.kt
    \bin\main\TicTacToe\T3.kt
    \src\main\kotlin\TicTacToe\App.kt
    \src\main\kotlin\TicTacToe\T3.kt
    \src\main\resources\public\index.html

Fügen Sie dieses Resultat in Ihre Dokumentation wie oben gezeigt ein. Sollten Sie Dateien dabei haben mit einem Pfad wie z.B. `\.gradle\...` oder auch `\.git\...`, dann beachten Sie: Diese Dateien gehören nicht zur Abgabe und sollten auch nicht gelistet werden. Das sind Dateien, die Gradle, die Versionsverwaltung oder auch die Entwicklungsumgebung anlegen, die uns nicht interessieren.

### Zur Übersicht der Lines of Code (LoC)

Fügen Sie darüber hinaus die mit `cloc .` erzeugte Übersicht zu den Lines of Code in Ihrer Dokumentation ein; achten Sie wieder auf die Einrückung. Infos zur `cloc` finden Sie unter https://github.com/AlDanial/cloc.

### Quellenangaben

Es reicht z.B. für den Verweis auf die Processing-Befehle die Seite https://processing.org/reference/ zu referenzieren. Es ist nicht sinnvoll jede verwendete Processing-Methode aufzuführen.

Wenn Sie Infos beispielsweise auf https://stackoverflow.com/ recherchiert haben, dann gehören die entsprechende Seite zu den verwendeten Quellen.

## Erfahrungs-Tipps

Mittlerweile haben wir einige Erfahrungen mit studentischen Abgaben gesammelt. Beherzigen Sie bitte die Hinweise und die Tipps:

* Bitte verwenden Sie das `>` am Anfang eines Absatzes nur dann, wenn Sie den Absatz farblich aus einem guten Grund hervorheben wollen. Wenn jeder Absatz hervorgehoben wird, ist das Markdown-Dokument nicht sehr angenehm zu lesen.
* Bitte verwenden Sie innerhalb Ihres Abgabeordners nur relative Links, wenn Sie auf Dateien in Ihrem Abgabeordner verweisen. Absolute Links sind eigentlich nur für URLs passend.
* Überprüfen Sie vor der Abgabe, ob die Links im `README.md` funktionieren. Verschieben Sie Ihren Abgabeordner auf Ihrem Rechner an einen anderen Ort im Dateisystem und probieren Sie dann, ob die Links noch funktionieren.
* Bitte auf gar keinen Fall Dateien abgeben, die das Ergebnis einer Kompilierung der Quellcode-Dateien (bzw. Testcode-Dateien) sind.
* Wenn Sie Ihr Projekt mit git verwaltet haben, entfernen Sie den meist unsichtbaren Ordner `.git` aus Ihrem Verzeichnis
* Eine Daumenregel: Wenn Ihre `zip`-Datei mehrere Hundert Kilobyte (KB) groß ist, dann haben Sie vermutlich Dateien mit eingepackt, die nicht zur Abgabe gehören.

Was zeichnet eine gute Dokumentation aus? Lesbarkeit, Verständlichkeit, eine klare Gliederung und Struktur, Korrektheit der Sachinformationen, eine Abwesenheit von Schreib- und Grammatikfehlern. Und: Die Dokumentation soll für fachinformierte Leser*innen geschrieben sein. Hilfreich, erklärend und dennoch auf den Punkt gebracht. 

Viel Erfolg!