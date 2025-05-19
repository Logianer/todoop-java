# Todo-App in Java

## Anforderungen:

- [ ] Die TODO-App soll mehrere TODO-Listen verwalten können.
- [ ] Jede TODO-Liste hat einen Titel.
- [ ] Listen können weiterhin entweder Fließtext sein *(Musskriterium)*
- [ ] ...oder aus einer Reihe von Einträgen mit Checkbox bestehen *(Wunschkriterium 1)*.
  - [ ] Die abgehakten Checkbox-Einträge werden an das Ende der Liste sortiert, sobald die Checkbox aktiviert wurde
  - [ ] und anders dargestellt (z.B. Durchgestrichen, andere Farbe, ...).
- [ ] *Wunschkriterium 2* Die App speichert alle Einträge persistent, d.h. bei Wiederaufruf nach Beenden sind alle vergangenen Einträge wieder sichtbar

## Bewertung

- Entwickler-Dokumentation **20%**
  - [ ] JavaDoc, readthedocs...
  - [ ] Erklärung der wichtigsten Funktionen (mit Text, ganze Sätze)
  - [ ] Klassendiagramm
- Programmierung und Funktionalität **60%**
  - Codestruktur, Codeumfang, Lesbarkeit
  - Umsetzung und Funktionalität
    - Musskriterien: 30%
    - Je Wunschkriterium 15%
- Übung **20%**
  - Je eine Aufgabe aus den Themen 01…05 lösen
  - Abgabe als `.jar`-Datei: Packages `uebung.themaN`, je Package eine Klasse Main (also `uebung.thema01.Main`, `uebung.thema02.Main`,...), die die das Ergebnis demonstriert
