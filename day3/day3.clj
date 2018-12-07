#!/usr/bin/env boot

(require '[clojure.string :as string])

(def parser-regex #"#([0-9]*) @ ([0-9]*),([0-9]*): ([0-9]*)x([0-9]*)")

(defn to-int [x] (Integer/parseInt x))

(defn parse-line [line]
  (let [ [all id x y width height] (re-matches parser-regex line) ] 
    {:id (to-int id) 
     :min-x (to-int x) 
     :min-y (to-int y) 
     :max-x (+ (to-int x) (to-int width)) 
     :max-y (+ (to-int y) (to-int height))
     :height height
     :width width }))

(defn parse-input []
  (->> (slurp "input")
       (string/split-lines)
       (map parse-line)))

(defn intersect? [ x y {min-x :min-x min-y :min-y max-x :max-x max-y :max-y}]
  (and (<= min-x x (dec max-x)) (<= min-y y (dec max-y))))

(defn update-if [f k v m]
  (if (f (k m)) (update m k (constantly v))
    m))

(defn find-min-max-x-y [lines]
  (reduce (fn [result 
               {min-x :min-x
                min-y :min-y 
                max-x :max-x 
                max-y :max-y }]
            (->> result
                 (update-if (partial < min-x) :min-x min-x)
                 (update-if (partial < min-y) :min-y min-y)
                 (update-if (partial > max-x) :max-x max-x)
                 (update-if (partial > max-y) :max-y max-y))) 
          {:min-x 9999 :min-y 9999 :max-x 0 :max-y 0} lines))

(defn to-x-y [width i] 
  (vector (mod i width) (int (/ i width))))

(defn to-index [width x y]
  (+ (* width y) x))

(defn problem1 []
       (let [pieces (parse-input)
             {min-x :min-x 
              min-y :min-y 
              max-x :max-x 
              max-y :max-y } (find-min-max-x-y pieces)]
         (->> (range (to-index max-x min-x min-y) (dec (to-index max-x max-x max-y)))
              (map (partial to-x-y max-x))
              (pmap (fn [[x y]] (count (filter (partial intersect? x y) pieces))))
              (filter #(< 1 %1))
              (count)
              )))

(defn -main [& args]
  (println (problem1)))
