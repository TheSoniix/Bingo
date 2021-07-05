package Bingo.Engine;

import Bingo.Engine.Models.Field;

import java.util.List;

/**
 * Das Interface bietet die komplette Antriebslogik für ein ein Bingo-Spiel (75-Ball Variante),
 * bei welchem zwei Spieler gegeneinander antreten.
 * Der Nutzer hat die Kontrolle darüber wann eine Kugel gezogen wird oder wann, welcher Spieler, ein
 * Feld markiert. Ob ein Feld markiert wird hängt davon ab, ob bereits eine Kugel gezogen wurde,
 * dessen Zahl auch der Zahl des Feldes entspricht.
 * Der Sieger wird automatisch ermittelt. Man gewinnt, wenn fünf Felder horizontal, vertikal oder
 * diagonal in einer Reiher markiert wurden. Ein Untentschieden ist bei Bingo nicht möglich.
 * Sollten beide Spieler in der gleichen Runde das fünfte Feld markieren, so gewinnt derjenige, der
 * sein Feld zuerst markierte.
 */

public interface Bingo {

    /**
     * Stellt die Bingo-Karte beider Spieler als String dar. Dabei wird ein markiertes Feld als
     * 'X' dargestellt.
     *
     * @return ein String von beiden Karten
     */
    String toString();

    /**
     * Setzt alle Kugeln zurück und erstellt jedem Spieler eine neue Bingo-Karte.
     */
    void newGame();

    /**
     * Gibt eine Liste aus Field's zurück, welche die Bingo-Karte des ersten Spielers darstellt.
     * Ein Feld besteht dabei aus dem Index, dem Inhalt, einer booleschen Variable, die angibt,
     * ob ein Feld markiert ist und einer weiteren booleschen Variable die anzeigt, ob dieses
     * Feld eines aus der Sieger-Reihe ist. Diese Werte kann man mit getIndex(), getValue(),
     * isMarked() und isWinner() erhalten. Ebenfalls kann man Methoden wie setMark() oder
     * setWinningField() benutzen, jedoch hätte diese Art von Manipulation keinen Einfluss auf das
     * Spielgeschehen, da dadurch die Liste der Bingo implementierung nicht geändert wird.
     *
     * @return eine Liste aus Field's von dem ersten Spieler
     */
    List<Field> pOneCard();

    /**
     * Gibt eine Liste aus Field's zurück, welche die Bingo-Karte des zweiten Spielers darstellt.
     * Ein Feld besteht dabei aus dem Index, dem Inhalt, einer booleschen Variable, die angibt,
     * ob ein Feld markiert ist und einer weiteren booleschen Variable die anzeigt, ob dieses
     * Feld eines aus der Sieger-Reihe ist. Diese Werte kann man mit getIndex(), getValue(),
     * isMarked() und isWinner() erhalten. Ebenfalls kann man Methoden wie setMark() oder
     * setWinningField() benutzen, jedoch hätte diese Art von Manipulation keinen Einfluss auf das
     * Spielgeschehen, da dadurch die Liste der Bingo implementierung nicht geändert wird.
     *
     * @return eine Liste aus Field's von dem zweiten Spieler
     */
    List<Field> pTwoCard();

    /**
     * Markiert ein Feld des ersten Spielers. Das Feld wird dabei nur markiert, wenn die Zahl des
     * Feldes einem der bereits gezogenen Bällen entspricht. Sollte das Spiel bereits zu Ende sein,
     * dürfen keine Felder mehr markiert werden. Das markieren würde eine Fehler werfen.
     *
     * @param index ist der Index des Feldes, welches markiert werden soll. Werte oberhalb der
     *              Gesamtzahl aller Felder einer Liste oder negativen Zahlen, werden abgefangen und
     *              ein Fehler wird geworfen.
     */
    void pOneMarkField(int index);

    /**
     * Markiert ein Feld des zweiten Spielers. Das Feld wird dabei nur markiert, wenn die Zahl des
     * Feldes einem der bereits gezogenen Bällen entspricht. Sollte das Spiel bereits zu Ende sein,
     * dürfen keine Felder mehr markiert werden. Das markieren würde eine Fehler werfen.
     *
     * @param index ist der Index des Feldes, welches markiert werden soll. Werte oberhalb der
     *              Gesamtzahl aller Felder einer Liste oder negativen Zahlen, werden abgefangen und
     *              ein Fehler wird geworfen.
     */
    void pTwoMarkField(int index);

    /**
     * Zieht eine Kugel und gibt die aktuell gezogene Zahl zurück. Da dies die 75-Ball Variante
     * ist, darf maximal nur 75 Mal gezogen werden. Ein weiterer Zug führt zu einem Fehler.
     * Ebenfalls soll, nachdem das Spiel zu ende ist, auch nicht mehr gezogen werden. Dies würde
     * ebenfalls zu einem Fehler führen.
     *
     * @return die aktuell gezogene Zahl
     */
    int pullBall();

    /**
     * Diese Methode zeigt, ob das Spiel zu ende ist oder nicht.
     *
     * @return eine boolean. Bei "true" ist das Spiel vorbei, bei "false" noch nicht.
     */
    boolean isGameOver();

    /**
     * Gibt eine Liste aller bereits gezogenen Kugeln in einer Liste von Zahlen zurück.
     *
     * @return eine Liste von Integers von bereits gezogenen Kugeln
     */
    List<Integer> pulledBalls();

    /**
     * Gibt eine Liste von Kugeln zurück, die noch nicht gezogen wurden.
     *
     * @return eine Liste von Integers von noch nicht gezogenen Kugeln.
     */
    List<Integer> notPulledBalls();

    /**
     * Gibt an, ob der erste Spieler oder der zweite Spieler gewonnen hat.
     *
     * @return ein boolean. Bei "true" ist der erste Spieler der Sieger. Bei "false" ist der zweite
     * Spieler der Sieger.
     */
    boolean isPOneWinner();


}
