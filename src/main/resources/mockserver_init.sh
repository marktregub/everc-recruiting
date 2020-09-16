curl -v -X PUT "http://localhost:1080/mockserver/expectation" -d '{
    "httpRequest": {
        "path": "/test_api",
		    "method": "POST"
    },
    "httpResponse": {
        "body": "{\"score\": 0.85, \"category\": \"pharma\", \"risk\": \"HIGH\"}",
        "delay": {
            "timeUnit": "SECONDS",
            "value": 1
        }
    },
    "times": {
        "remainingTimes": 10,
        "unlimited": false
    }
}'

curl -v -X PUT "http://localhost:1080/mockserver/expectation" -d '{
    "httpRequest": {
        "path": "/test_api",
		"method": "POST"
    },
    "httpResponse": {
        "body": "{\"score\": 0.9, \"category\": \"clothing\", \"risk\": \"LOW\"}",
        "delay": {
            "timeUnit": "SECONDS",
            "value": 2
        }
    },
    "times": {
        "remainingTimes": 20,
        "unlimited": false
    }
}'

curl -v -X PUT "http://localhost:1080/mockserver/expectation" -d '{
    "httpRequest": {
        "path": "/test_api",
		"method": "POST"
    },
    "httpResponse": {
        "body": "{\"score\": 0.65, \"category\": \"alchohol\", \"risk\": \"MEDIUM\"}",
        "delay": {
            "timeUnit": "SECONDS",
            "value": 3
        }
    },
    "times": {
        "remainingTimes": 10,
        "unlimited": false
    }
}'

curl -v -X PUT "http://localhost:1080/mockserver/expectation" -d '{
    "httpRequest": {
        "path": "/test_api",
		"method": "POST"
    },
    "httpResponse": {
        "body": "{\"score\": 0.85, \"category\": \"pharma\", \"risk\": \"HIGH\"}",
        "delay": {
            "timeUnit": "SECONDS",
            "value": 1
        }
    },
    "times": {
        "remainingTimes": 100,
        "unlimited": false
    }
}'

curl -v -X PUT "http://localhost:1080/mockserver/expectation" -d '{
    "httpRequest": {
        "path": "/test_api",
		"method": "POST"
    },
    "httpResponse": {
        "body": "{\"score\": 0.9, \"category\": \"clothing\", \"risk\": \"LOW\"}",
        "delay": {
            "timeUnit": "SECONDS",
            "value": 2
        }
    },
    "times": {
        "remainingTimes": 200,
        "unlimited": false
    }
}'

curl -v -X PUT "http://localhost:1080/mockserver/expectation" -d '{
    "httpRequest": {
        "path": "/test_api",
		"method": "POST"
    },
    "httpResponse": {
        "body": "{\"score\": 0.65, \"category\": \"alchohol\", \"risk\": \"MEDIUM\"}",
        "delay": {
            "timeUnit": "SECONDS",
            "value": 3
        }
    },
    "times": {
        "remainingTimes": 100,
        "unlimited": false
    }
}'

curl -v -X PUT "http://localhost:1080/mockserver/expectation" -d '{
    "httpRequest": {
        "path": "/test_api",
		"method": "POST"
    },
    "httpResponse": {
        "body": "{\"score\": 0.85, \"category\": \"pharma\", \"risk\": \"HIGH\"}",
        "delay": {
            "timeUnit": "SECONDS",
            "value": 1
        }
    },
    "times": {
        "remainingTimes": 1000,
        "unlimited": false
    }
}'

curl -v -X PUT "http://localhost:1080/mockserver/expectation" -d '{
    "httpRequest": {
        "path": "/test_api",
		"method": "POST"
    },
    "httpResponse": {
        "body": "{\"score\": 0.9, \"category\": \"clothing\", \"risk\": \"LOW\"}",
        "delay": {
            "timeUnit": "SECONDS",
            "value": 2
        }
    },
    "times": {
        "remainingTimes": 2000,
        "unlimited": false
    }
}'

curl -v -X PUT "http://localhost:1080/mockserver/expectation" -d '{
    "httpRequest": {
        "path": "/test_api",
		"method": "POST"
    },
    "httpResponse": {
        "body": "{\"score\": 0.65, \"category\": \"alchohol\", \"risk\": \"MEDIUM\"}",
        "delay": {
            "timeUnit": "SECONDS",
            "value": 3
        }
    }
}'


curl -v -X PUT "http://localhost:1080/mockserver/expectation" -d '{
    "httpRequest": {
        "path": "/item",
		"method": "POST",
		"body": {
            "type": "JSON"
		}
    },
    "httpResponse": {
        "body": "Thanks for submitting item scan result."
    }
}'

curl -v -X PUT "http://localhost:1080/mockserver/expectation" -d '{
    "httpRequest": {
        "path": "/batch",
		"method": "POST",
		"body": {
		    "type": "JSON"
		  }
		},
    "httpResponse": {
        "body": "Thanks for submitting batch complete."
    }
}'