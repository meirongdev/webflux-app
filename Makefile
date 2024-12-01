.PHONY: help build build-jar build-image up down up-db clean-docker

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

up-db: ## Start only the PostgreSQL service
	docker compose up -d db

clean-docker: ## Clean up all Docker resources
	docker compose down -v --rmi all --remove-orphans
	docker system prune -f --volumes