#!/bin/sh
#
# This tool parses the sorbs.csv file to generate enums for the
# SorbsBlacklists enum.
#
awk -f sorbs-parser.awk sorbs.csv