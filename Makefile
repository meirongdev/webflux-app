.PHONY: help build build-jar build-image up down up-locally down-locally clean-docker

help: ## Display this help message
	@echo "Usage:"
	@echo "  make <target>"
	@echo ""
	@echo "Targets:"
	@grep -E '^[a-zA-Z_-]+:.*?## .*$$' $(MAKEFILE_LIST) | awk 'BEGIN {FS = ":.*?## "}; {printf "  %-15s %s\n", $$1, $$2}'

build: build-jar build-image ## Build the application and Docker image
	@echo "Build completed"

build-jar: ## Build the application locally
	mvn clean package -DskipTests

build-image: ## Build the Docker image
	docker build -t meirongdev/webflux-app .

up: ## Start the Docker Compose services
	docker compose up -d

down: ## Stop the Docker Compose services
	docker compose down

up-locally: ## Start locally only the PostgreSQL service, Redis service
	docker compose up -d db redis

down-locally: ## Stop locally only the PostgreSQL service, Redis service
	docker compose down db redis

clean-docker: ## Clean up all Docker resources
	docker compose down -v --rmi all --remove-orphans
	# docker system prune -f --volumes