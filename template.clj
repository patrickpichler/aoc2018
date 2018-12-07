#!/usr/bin/env boot

(defn parse-input []
  (->> (slurp "input")
       (string/split-lines)))

(defn problem1 []
       (parse-input))

(defn -main [& args]
  (println (problem1)))
