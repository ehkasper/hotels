(ns hotel-reservation.core-test
  (:require [clojure.test :refer :all]
            [hotel-reservation.core :refer :all]))

(deftest hotel-structures
  (testing "hotel objects"
     (lakewood { :name            "Lakewood"
                 :classification  3
                 :weekdays        110
                 :weekdaysReward  80
                 :weekends        90
                 :weekendsReward  80 })
     (bridgewood { :name            "Bridgewood"
                   :classification  4
                   :weekdays        160
                   :weekdaysReward  110
                   :weekends        60
                   :weekendsReward  50 })
     (ridgewood { :name             "Ridgewood"
                  :classification   5
                  :weekdays         220
                  :weekdaysReward   100
                  :weekends         150
                  :weekendsReward   40 })))

(deftest parsing-input
  (testing "should return true when input is a weekday"
           (is (is-weekday? "mon"))
           (is (is-weekday? "tues"))
           (is (is-weekday? "wed"))
           (is (is-weekday? "thu"))
           (is (is-weekday? "fri")))

  (testing "should return false when input is a weekend"
      (is (= false (is-weekday? "sat")))
      (is (= false (is-weekday? "sun"))))

  (testing "should extract day from string")
      (is (= (get-day "16Mar2009(mon)") "mon"))
      (is (= (get-day "16Mar2009(tues)") "tues"))

  (testing "should return a list of dates when line is inputed"
      (is (= ["date1", "date2"] (get-dates "Reward: date1, date2"))))

  (testing "should validate input"
      (not (is-valid-day "date"))
      (is (is-valid-day "16Mar2009(mon)")))

  (testing "should extract type of client"
      (is (= "Reward" (get-client-type "Reward: 16Mar2009(mon)")))
      (is (= "Regular" (get-client-type "Regular: 16Mar2009(mon)"))))

  (testing "should validate type of client"
      (is (is-valid-client-type "Reward"))
      (is (is-valid-client-type "Regular"))
      (not (is-valid-client-type "other"))))

(deftest processing-dates
  (testing "should return a list of days when dates are given"
     (is (= (get-days ["16Mar2009(mon)", "17Mar2009(tue)"]) ["mon", "tue"])))

  (testing "should return total sum, for given days to hotel"
    (is (= (sum-of lakewood "Regular" ["mon"]) 110))
    (is (= (sum-of lakewood "Regular" ["fri", "sat"]) 200))
    (is (= (sum-of lakewood "Reward" ["fri"]) 80))
    (is (= (sum-of lakewood "Reward" ["fri", "sat"]) 160))
    (is (= (sum-of bridgewood "Reward" ["fri", "sat"]) 160)))

  (def regular-summed-hotels
    [{ :name "Lakewood" :total 110 :classification 3}
     { :name "Bridgewood" :total 160 :classification 4}
     { :name "Ridgewood" :total 220 :classification 5}])

  (def reward-summed-hotels
    [{ :name "Lakewood" :total 80 :classification 3}
     { :name "Bridgewood" :total 110 :classification 4}
     { :name "Ridgewood" :total 100 :classification 5}])

  (testing "should receive the whole input and return objects with total, classification and hotel name when client is regular"
           (is (= (get-hotels "Regular: 16Mar2009(mon)") regular-summed-hotels)))

  (testing "should receive the whole input and return objects with total, classification and hotel name when client is reward"
           (is (= (get-hotels "Reward: 16Mar2009(mon)") reward-summed-hotels)))

  (testing "should sort hotels by total key"
           (is (= (sort-hotels reward-summed-hotels) [{ :name "Lakewood" :total 80 :classification 3}
                                                      { :name "Ridgewood" :total 100 :classification 5}
                                                      { :name "Bridgewood" :total 110 :classification 4}])))
)

