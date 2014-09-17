# Sahi

## Requirements

- Java 7
- Maven

sahi-core:

- Unix like OS

sahi-test-webapp:

- Firefox
- [certutil](https://developer.mozilla.org/en-US/docs/Mozilla/Projects/NSS/tools/NSS_Tools_certutil#__Availability_)

## Setting up the workspace

### With IntelliJ

- clone Sahi from this git repo
- import the project [how?](http://www.jetbrains.com/idea/webhelp/importing-project-from-maven-model.html)
- set the default run/debug configuration to use the sahi-core module as default working directory (or else your unittests will fail and the dashboard wont start) [how?](http://youtrack.jetbrains.com/issue/IDEA-52112)
- run the Dashboard.main()

## Packaging and running Sahi

Run

    cd sahi-core
    mvn package verify
    cd bin
    sh dashboard.sh

Then the required jars are built to run sahi with the scripts under sahi-core/bin.

Licenses
--------

See sahi/docs/licenses

Notes
-----
This was pulled from svn using [svn2git](https://github.com/nirvdrum/svn2git), so all prior subversion commits are available
