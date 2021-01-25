echo "Creating App Engine app"
gcloud app create --region "us-central"

echo "Exporting GCLOUD_PROJECT"
export GCLOUD_PROJECT=$DEVSHELL_PROJECT_ID

echo "Installing dependencies"
mvn clean install -P pro
cp target/mutant-challenge-api-sprgbt-1.0.0.jar .

echo "Creating Container Engine cluster"
gcloud container clusters create challenge-cluster --zone us-central1-a --scopes cloud-platform
gcloud container clusters get-credentials challenge-cluster --zone us-central1-a

echo "Building Containers"
gcloud builds submit -t gcr.io/$DEVSHELL_PROJECT_ID/mutant-challenge-api-sprgbt .

echo "Deploying to Container Engine"
sed -i -e "s/\[GCLOUD_PROJECT\]/$DEVSHELL_PROJECT_ID/g" ./object-K8s-configurations/backend-deployment.yaml
kubectl create -f ./object-K8s-configurations/backend-deployment.yaml
kubectl create -f ./object-K8s-configurations/service.yaml


echo "Project ID: $DEVSHELL_PROJECT_ID"

# restart process
# rm -rf mutant-challenge-api-sprgbt-1.0.0.jar
# gcloud container clusters delete challenge-cluster --zone us-central1-a
# kubectl delete -f ./object-K8s-configurations/backend-deployment.yaml
# kubectl delete -f ./object-K8s-configurations/service.yaml