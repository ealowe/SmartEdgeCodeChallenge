To build and run this project:<br>
$ ./build_it.sh<br>
$ ./run_it.sh my-message<br>
<p>
And now, I have to admit that I've achieved only partial success.  While the application works, it does not completely fulfill the stated requirements.<br>
1) Instead of living in a Docker container, it runs on the command line.<br>
2) It generates its keys on the fly with every invocation and does not store them on disk.<br>
3) Unit testing is thin.<br>
<p>
A little explanation is in order, as I had to climb several learning curves to build this project:
<p>
- I went with Java since that's my strongest language.  I considered Perl as that's much better at text processing, but it's harder to write good tests.<br>
- My experience with private/public keys (and security and encryption in general) is limited.  Most of my work has been deep in the back end.<br>
- This was my very first exposure to the java.security package.  It's a pain.<br>
- For java.security to store a KeyPair on disk in a KeyStore, I needed to generate a trusted certificate.  I've never worked with trusted certificates, and Java doesn't offer much support for it out of the box.  I looked at the Bouncy Castle library, but decided to punt it until later and get the main app working first.<br>
- I chose Eclipse as my IDE, but it's been six years since I've used it and I was rusty (I've been using IntelliJ for the past few years, and I'm much more used to its editor.  It's also a lot speedier than Eclipse).<br>
- I hadn't worked with Docker either, but decided to figure out the deployment once I had the app working.<br>
<p>
I spent 8-10 hours on this project over the course of three nights, and I finally decided just couldn't devote any more time to it.  Given more time I could have figured out the remaining pieces, but once I got to a working command-line application, I had to make the executive decision that this would have to suffice.
<p>
Maybe in the next sprint.  :)
<p>
Thanks.
