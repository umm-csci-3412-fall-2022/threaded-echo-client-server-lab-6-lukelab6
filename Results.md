# Experimental results

Two servers were compared, the server I created for the assignment with multi-threading and
the server in test/sampleBin which only has one thread. I used the words.txt file with 10000
words to test the speed of the server. 
Although I cannot see the tenths of a second, for 10 connections the threaded took 1 second and the unthreaded
took 2. For 50 connections, the threaded took 10 seconds and unthreaded took 12. For 100 connections, threaded
took 20 and unthreaded took 28. This suggests that the threaded is faster than the unthreaded in every load condition
which makes sense, but it does not appear to be a linear scaling performance increase that is easy to predict.
