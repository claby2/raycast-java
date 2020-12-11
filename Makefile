JFLAGS = -g
JC = javac
.SUFFIXES: .java .class
.java.class:
	$(JC) $(JFLAGS) $*.java

CLASSES = \
		  Panel.java       \
		  Player.java      \
		  Position.java    \
		  RayRenderer.java \
		  Wall.java        \
		  WallManager.java \
		  Main.java        \

default: classes

classes: $(CLASSES:.java=.class)

clean:
	$(RM) *.class

