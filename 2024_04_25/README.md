# Validierung des Schiffe-Versenken-Bretts

Schreiben Sie eine Methode, die ein Feld für das bekannte Brettspiel "Schiffe-Versenken" als Argument akzeptiert und `true` zurückgibt, wenn es eine gültige Anordnung von Schiffen hat, `false` andernfalls. Das Argument ist garantiert ein 10x10 zweidimensionales Array. Elemente im Array sind Zahlen, `0`, wenn die Zelle frei ist, und `1`, wenn sie von einem Schiff besetzt ist.

## Spielübersicht

**Schiffe-Versenken** (auch bekannt als **Battleships**) ist ein Ratespiel für zwei Spieler. Jeder Spieler hat ein 10x10-Gitter, das mehrere "Schiffe" enthält, und das Ziel besteht darin, die Kräfte des Gegners zu zerstören, indem Zellen auf seinem Feld angegriffen werden. Ein Schiff belegt eine oder mehrere Zellen im Gitter. Die Größe und Anzahl der Schiffe können von Version zu Version variieren. In dieser Übung werden wir die sowjetische/russische Version des Spiels verwenden.

## Regeln für die Schiffsplatzierung

Bevor das Spiel beginnt, richten die Spieler das Brett ein und platzieren die Schiffe gemäß den folgenden Regeln:

- Es muss ein einziges Schlachtschiff (Größe von 4 Zellen), 2 Kreuzer (Größe 3), 3 Zerstörer (Größe 2) und 4 U-Boote (Größe 1) geben. Zusätzliche Schiffe sind nicht erlaubt, ebenso wie fehlende Schiffe.
- Jedes Schiff muss eine gerade Linie sein, außer für U-Boote, die nur eine einzelne Zelle sind.
- Das Schiff darf sich nicht mit einem anderen Schiff überlappen oder berühren, weder an der Kante noch an der Ecke.

## Schlussfolgerung

Das ist alles, was Sie für diese Übung benötigen. Wenn Sie mehr Informationen über das Spiel wünschen, besuchen Sie [diesen Link](#).
