PROJET=Competition
AUTHORS=pather-larafi
JC=javac
JVM=java
JVDOC=javadoc -d ../doc
MAKE=make
JARFLAGS=-jar
JFLAGS= -g -d
JUNITFLAGS=--disable-banner --scan-classpath
JUNIT5=test-1.8.1.jar
CLASSPATH=classes/
SRC=cd src
CLASS=cd classes
DOC=doc/
LIB=*/*.java LeagueMain.java TournamentMain.java MasterMain.java ObserverMain.java
LIBTEST=src:test test/*/*.java
CLASSLIB=competition/* match/* util/* person/* selection/* observer/*
SOURCEPATH=-sourcepath
ARCHIVE_FILES= src/*/* test/*/* img/* Makefile README.md README_v1.md README_v2.md README_v3.md manifest_league manifest_tournament manifest_master manifest_observer $(JUNIT5)
MAIN_LEAGUE=league.jar
MAIN_TOURNAMENT=tournament.jar
MAIN_MASTER=master.jar
MAIN_OBSERVER=observer.jar

all: library league_jar tournament_jar master_jar observer_jar

library:
	$(SRC) && $(JC) $(JFLAGS) ../$(CLASSPATH) $(LIB)

league_jar:
	$(CLASS) && jar cvf ../$(MAIN_LEAGUE) $(CLASSLIB) LeagueMain.class
	$(CLASS) && jar cvfm ../$(MAIN_LEAGUE) ../manifest_league $(CLASSLIB) LeagueMain.class

tournament_jar:
	$(CLASS) && jar cvf ../$(MAIN_TOURNAMENT) $(CLASSLIB) TournamentMain.class
	$(CLASS) && jar cvfm ../$(MAIN_TOURNAMENT) ../manifest_tournament $(CLASSLIB) TournamentMain.class

master_jar:
	$(CLASS) && jar cvf ../$(MAIN_MASTER) $(CLASSLIB) MasterMain.class
	$(CLASS) && jar cvfm ../$(MAIN_MASTER) ../manifest_master $(CLASSLIB) MasterMain.class

observer_jar:
	$(CLASS) && jar cvf ../$(MAIN_OBSERVER) $(CLASSLIB) ObserverMain.class
	$(CLASS) && jar cvfm ../$(MAIN_OBSERVER) ../manifest_observer $(CLASSLIB) ObserverMain.class

test:
	$(JC) -cp $(JUNIT5):class $(SOURCEPATH) $(LIBTEST) -d $(CLASSPATH)
	$(JVM) $(JARFLAGS) $(JUNIT5) -cp $(CLASSPATH) $(JUNITFLAGS)

doc: 
	$(SRC) && $(JVDOC) $(LIB)

archive: projet-$(AUTHORS)-$(PROJET).zip

projet-$(AUTHORS)-$(PROJET).zip: $(ARCHIVE_FILES)
	zip $@ $(ARCHIVE_FILES)

clean:
	rm -rf $(CLASSPATH)
	rm -f $(MAIN_LEAGUE) $(MAIN_TOURNAMENT) $(MAIN_MASTER) $(MAIN_OBSERVER)

cleanall: clean
	rm -rf $(DOC)
	rm -f projet-$(AUTHORS)-$(PROJET).zip

.PHONY: clean cleanall archive all test doc library league_jar tournament_jar master_jar observer_jar
