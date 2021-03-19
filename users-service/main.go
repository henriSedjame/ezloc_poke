package main

import (
	"encoding/json"
	"github.com/gorilla/mux"
	"net/http"
)

type User struct {
	Id string `json:"id"`
	FirstName string `json:"firstname"`
	LastName  string `json:"lastname"`
	Age       int8   `json:"age"`
}

func getUsers(w http.ResponseWriter, r *http.Request) {
	users := []User{
		{
			FirstName: "Joel",
			LastName:  "Henri",
			Age:       34,
		},
		{
			FirstName: "Chloe",
			LastName:  "Anaïs",
			Age:       30,
		},
	}
	_ = json.NewEncoder(w).Encode(users)
}

func main() {
	r := mux.NewRouter()

	r.HandleFunc("/service/users", getUsers).Methods("GET")

	_ = http.ListenAndServe(":8080", r)
}
