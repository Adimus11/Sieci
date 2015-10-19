package main

import (
	"fmt"
	"io/ioutil"
	"net/http"
)

func handler(w http.ResponseWriter, r *http.Request) {

	location := r.URL.Path[1:]

	file, err := ioutil.ReadFile(location)

	if err != nil {

		printHeader(r)

		http.NotFound(w, r)

	} else {

		s := string(file)

		fmt.Fprintf(w, "%s", s)

		printHeader(r)

	}
}

func main() {
	fmt.Println("Program nasłuchuje na porcie 8080")
	http.HandleFunc("/", handler)
	http.ListenAndServe(":8080", nil)
}

func printHeader(h *http.Request) {

	fmt.Printf("\n\n")

	fmt.Printf("%v %v %v %v", h.Method, h.URL, h.Proto, "\n")
	fmt.Printf("%v %v %v", "Host: ", h.Host, "\n")

	for key, value := range h.Header {

		toPrint := key + ": "

		for _, arrayItem := range value {

			toPrint += arrayItem + " "

		}

		fmt.Printf(toPrint + "\n")

	}

}
