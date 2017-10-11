{
    FILL="    "
    FILL2=FILL " " FILL

    if (NR != 1) {
        printf(",\n\n");
    }

    # print the enum name
    split($1,names,/\./); 
    if (length(names) == 3) {
        printf("%s%s_%s(\n", FILL, toupper(names[2]), toupper(names[1]));
    } else {
        printf("%s%s_%s_%s(\n", FILL, toupper(names[length(names)-1]), toupper(names[1]), toupper(names[2]));
    }

    # print the hostname and the description
    printf("%s\"%s\",\n%s\"%s\",\n", FILL2, $1, FILL2, $2);

    # print the result code(s)
    for (i=3; i<NF; i++) {
        printf("%s\"127.0.0.%s\",\n", FILL2, $i);
    }
    printf("%s\"127.0.0.%d\"\n%s)", FILL2, $(NF), FILL);
}
