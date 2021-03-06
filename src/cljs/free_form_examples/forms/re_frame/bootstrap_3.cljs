;;;; Copyright © 2015 Carousel Apps, Ltd. All rights reserved.

(ns free-form-examples.forms.re-frame.bootstrap-3
  (:require [reagent.ratom :as ratom :include-macros true]
            [re-frame.core :as re-frame]
            [free-form.re-frame :as free-form]
            [free-form-examples.layout :as layout]
            [free-form-examples.routing :as routing]))

(re-frame/register-sub
  :re-frame-bootstrap-3
  (fn [db]
    (ratom/reaction (:re-frame-bootstrap-3 @db))))

(re-frame/register-handler
  :update-re-frame-bootstrap-3
  (fn [db [_ keys value :as event]]
    (-> db
        (assoc-in (cons :re-frame-bootstrap-3 keys) value)
        (update :event-log #(conj % event)))))

(defmethod layout/pages :re-frame-bootstrap-3 [_]
  (let [data (re-frame/subscribe [:re-frame-bootstrap-3])]
    (fn []
      [:div
       [layout/source-code-button "re_frame/bootstrap_3.cljs"]
       [:h1 "Re-frame Bootstrap 3"]
       [:p "You might also want to check out the "
        [:a {:href (routing/url-for :re-frame-bootstrap-3-horizontal)} "Bootsrap horizontal"]
        " and "
        [:a {:href (routing/url-for :re-frame-bootstrap-3-inline)} "Bootsrap inline"]
        " versions of this form."]
       [free-form/form {} {} :update-re-frame-bootstrap-3
        [:form {:noValidate        true
                :free-form/options {:mode :bootstrap-3}}
         [:div.col-sm-offset-2.col-sm-10 {:free-form/error-message {:key :-general}} [:p.text-danger]]
         [:free-form/field {:type        :text
                            :key         :text
                            :label       "Text"
                            :placeholder "placeholder"}]
         [:free-form/field {:type        :email
                            :key         :email
                            :label       "Email"
                            :placeholder "placeholder@example.com"}]
         [:free-form/field {:type  :password
                            :label "Password"
                            :keys  [:password]}]
         [:button.btn.btn-primary {:type :submit} "Button"]]]
       [layout/state @data :re-frame-bootstrap-3]
       [layout/event-log]])))
