#!/bin/bash
set -xe

# Delete the old  directory as needed.
if [ -d /usr/local/codedeployresources ]; then
    sudo rm -rf /usr/local/codedeployresources/
fi

sudo mkdir -vp /usr/local/codedeployresources
