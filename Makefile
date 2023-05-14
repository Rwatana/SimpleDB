PROJECT_NAME=database_simpledb
IMAGE_NAME=ryoma_${PROJECT_NAME}
CONTAINER_NAME=ryoma_${PROJECT_NAME}
PORT=8886
SHM_SIZE=2g
FORCE_RM=true

build:
	docker build \
     --build-arg USER_ID=3045 \
     --build-arg GROUP_ID=1000 \
     -f docker/Dockerfile \
     -t ryoma_database_simpledb \
     --force-rm=true \
     .

run:
	docker run \
		-dit \
		-v $(PWD):/workspace \
		-p $(PORT):$(PORT) \
		--name $(CONTAINER_NAME) \
		--rm \
		--shm-size $(SHM_SIZE) \
		$(IMAGE_NAME)

exec:
	docker exec \
		-it \
		$(CONTAINER_NAME) /bin/bash

stop:
	docker stop $(IMAGE_NAME)

restart: stop run