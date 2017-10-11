#!/bin/sh
#
# This tool parses the blacklist.csv file to generate enums for the
# Blacklist enum.
#
# The format of the blacklist.csv file is expected to be in a format
# of:
# <DNS hostname>|<description>|<Result code 1 - last digit of 127.0.0.x>[|<Result code 2 ...>]
awk -F'|' -f blacklist-parser.awk blacklist.csv