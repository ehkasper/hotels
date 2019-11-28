(ns hotel-reservation.core)

(def lakewood
  { :classification 3 :weekdays 110 :weekdaysReward 80 :weekends 90 :weekendsReward 80 })

(def bridgewood
  { :classification 4 :weekdays 160 :weekdaysReward 110 :weekends 60 :weekendsReward 50 })

(def ridgewood
  { :classification 5 :weekdays 220 :weekdaysReward 100 :weekends 150 :weekendsReward 40 })


(defn foo
  "I don't do a whole lot."
  [x]
  (println x "Hello, World!"))
