#!/bin/bash

#race condition example
ab -n 1000 -c 50 localhost:8080/race-condition-cta

ab -n 10000 -c 50 localhost:8080/race-condition-rmw

#race condition fix example
ab -n 10000 -c 50 localhost:8080/race-condition-prv
