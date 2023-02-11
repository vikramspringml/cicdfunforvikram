
## Skaffold Build and Deploy to Google Cloud
### Pre-requisites
[GCloud CLI](https://cloud.google.com/sdk/docs/install), [Skaffold](https://skaffold.dev/docs/install/#standalone-binary) and [kubectl](https://cloud.google.com/kubernetes-engine/docs/how-to/cluster-access-for-kubectl) must be installed on the developer's laptop where the code is pulled.
### Overview
Skaffold has a profile defined that is specific to the developer by using a [Kustomize overlay](https://kubectl.docs.kubernetes.io/references/kustomize/glossary/#kustomization-root). In this example we have an overlay called "[cb](manifests/cb)" that specifically overrides the namespace and name suffix for the deployment/service defined in the [base](manifests/base/deployment.yaml). This can be extended to overlay or patch any existing yaml specified under [base](manifests/base) folder.

### 1. Run/Build the app with profile cloud-build
Run the following command to run the app. This will build the image with a tag remotely on cloudbuild (no need for docker to run locally).
```shell
skaffold run -p cloud-build
```
This will automatically generate a tag on Container registry and run the application on the GKE cluster as defined in the profile.

Alternatively, you can build the app if you are ready to deploy using skaffold

```shell
skaffold build -p cloud-build --file-output build.json
```

## 2. Deploy the app to the GKE cluster
a. Generate the rendered yaml to be deployed
```shell
skaffold render -a build.json --output render.yaml --digest-source local -p cloud-build
```

b. Deploy the rendered yaml

```shell
skaffold apply render.yaml
```

If the remote GKE cluster shows an error like "Get "https://34.28.111.145/version?timeout=32s": dial tcp 34.28.111.145:443: i/o timeout", this means that the developer's laptop is not added to the clusters Managed Authorized Network.

## Deploy on CloudBuild via Skaffold
Sample cloud build script to deploy to the GKE cluster using skaffold
```shell
gcloud builds submit . --substitutions=_GKE_CLUSTER_NAME=[[Cluster Name]],_GKE_CLUSTER_REGION=us-central1,SHORT_SHA=test1 --async
```