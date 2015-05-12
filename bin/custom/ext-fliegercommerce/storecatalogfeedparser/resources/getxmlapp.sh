#!/bin/sh

#PGRDIR=`dirname $0`/

#JRE_HOME=`dirname $0`/jre
#CLASSPATH="$PGMDIR/lib"
#JAVA_HOME=/usr

#echo "cd $PGRDIR"
#cd "$PGRDIR"

#echo "PGMDIR: $PGMDIR, PGRDIR: $PGRDIR"
#chmod a+x -R "$JRE_HOME"/bin

#echo $JAVA_HOME
#"$JAVA_HOME"/bin/java -classpath "$CLASSPATH" -jar "$PGRDIR"/storecatalogfeedparser.jar

HYBRIS_DIR=/HYBRIS/hering
STORE_CATALOG_DIR="$HYBRIS_DIR/bin/custom/storecatalogfeedparser"
ANT_DIR="$HYBRIS_DIR/bin/platform/apache-ant-1.9.1/bin"
JAVA_HOME=/usr/lib/jvm/java-7-oracle

$ANT_DIR/ant -buildfile "$STORE_CATALOG_DIR/build.xml"

if [ "$1" = "standalone" ]; then
	echo "Running Standalone Mode"
	#"$JAVA_HOME"/bin/java -classpath "$STORE_CATALOG_DIR"/lib -jar "$STORE_CATALOG_DIR"/build/storecatalogfeedparser.jar standalone
	cd "$STORE_CATALOG_DIR/build"
	"$JAVA_HOME"/bin/java -jar storecatalogfeedparser.jar standalone
else
	#"$JAVA_HOME"/bin/java -classpath "$STORE_CATALOG_DIR"/lib -jar "$STORE_CATALOG_DIR"/build/storecatalogfeedparser.jar
	cd "$STORE_CATALOG_DIR/build"
	"$JAVA_HOME"/bin/java -jar storecatalogfeedparser.jar
fi;
