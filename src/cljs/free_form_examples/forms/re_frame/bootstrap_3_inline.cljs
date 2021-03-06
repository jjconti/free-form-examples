;;;; Copyright © 2015 Carousel Apps, Ltd. All rights reserved.

(ns free-form-examples.forms.re-frame.bootstrap-3-inline
  (:require [reagent.ratom :as ratom :include-macros true]
            [re-frame.core :as re-frame]
            [free-form.re-frame :as free-form]
            [free-form-examples.layout :as layout]))

(re-frame/register-sub
  :re-frame-bootstrap-3-inline
  (fn [db]
    (ratom/reaction (:re-frame-bootstrap-3-inline @db))))

(re-frame/register-handler
  :update-re-frame-bootstrap-3-inline
  (fn [db [_ keys value :as event]]
    (-> db
        (assoc-in (cons :re-frame-bootstrap-3-inline keys) value)
        (update :event-log #(conj % event)))))

(defmethod layout/pages :re-frame-bootstrap-3-inline [_]
  (let [data (re-frame/subscribe [:re-frame-bootstrap-3-inline])]
    (fn []
      [:div
       [layout/source-code-button "re_frame/bootstrap_3_inline.cljs"]
       [:h1 "Re-frame Bootstrap 3 Inline"]
       [free-form/form {} {} :update-re-frame-bootstrap-3-inline
        [:form.form-inline {:noValidate        true
                            :free-form/options {:mode :bootstrap-3}}
         [:free-form/field {:type        :text
                            :key         :text
                            :label       "Text"
                            :placeholder "placeholder"}] " "
         [:free-form/field {:type        :email
                            :key         :email
                            :label       "Email"
                            :placeholder "placeholder@example.com"}] " "
         [:free-form/field {:type  :password
                            :label "Password"
                            :keys  [:password]}] " "
         [:button.btn.btn-primary {:type :submit} "Button"]]]
       [layout/state @data :re-frame-bootstrap-3-inline]
       [layout/event-log]])))
