{
    FS=";"

    if (NR != 1) {
        printf(",\n\n");
    }

    # print the enum name
    split($1,names,/\./); 
    if (length(names) == 3) {
        printf("  %s(\n", toupper(names[1]));
    } else {
        printf("  %s_%s(\n", toupper(names[1]), toupper(names[2]));
    }

    # print the hostname and the description
    printf("    \"%s\",\n    \"%s\",\n", $1, $2);

    # print the result code(s)
    for (i=3; i<NF; i++) {
        printf("    \"127.0.0.%s\",\n", $i);
    }
    printf("    \"127.0.0.%d\"\n  )", $(NF));
}
