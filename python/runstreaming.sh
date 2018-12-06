hadoop jar hadoop-streaming-2.6.5.jar \
 -file wcmapper.py \
 -mapper wcmapper.py \
 -file wcreducer.py \
 -reducer wcreducer.py \
 -input somewords.txt \
 -output wcstreaming-output