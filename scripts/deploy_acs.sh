#!/bin/bash

# Install kubernetes and set config
curl -LO https://storage.googleapis.com/kubernetes-release/release/$(curl -s https://storage.googleapis.com/kubernetes-release/release/stable.txt)/bin/linux/amd64/kubectl
chmod +x ./kubectl
sudo mv ./kubectl /usr/local/bin/kubectl

if [ $TRAVIS_BRANCH = "dev" ];
then
  mkdir ${HOME}/.kube
  cp .kube/config_dev ${HOME}/.kube/config

  # Fill out missing params in kubectl config file
  kubectl config set clusters.cci-uk-dev.certificate-authority-data "$KUBE_CLUSTER_CERTIFICATE_DEV"
  kubectl config set users.cci-uk-dev-admin.client-certificate-data "$KUBE_CLIENT_CERTIFICATE_DEV"
  kubectl config set users.cci-uk-dev-admin.client-key-data "$KUBE_CLIENT_KEY_DEV"

  #Do the deployment
  kubectl set image -n cms-fr deployment/auditlogproducer-fr-dev auditlogproducer-fr-dev=ccicreativeuk.azurecr.io/cci-audit-log-producer:$TRAVIS_BUILD_NUMBER
elif [ $TRAVIS_BRANCH = "master" ];
then
  mkdir ${HOME}/.kube
  cp .kube/config_test ${HOME}/.kube/config

  # Fill out missing params in kubectl config file
  kubectl config set clusters.cci-uk-qa.certificate-authority-data "$KUBE_CLUSTER_CERTIFICATE_TEST"
  kubectl config set users.cci-uk-qa-admin.client-certificate-data "$KUBE_CLIENT_CERTIFICATE_TEST"
  kubectl config set users.cci-uk-qa-admin.client-key-data "$KUBE_CLIENT_KEY_TEST"
  #update the image
  kubectl set image -n cms-fr deployment/auditlogproducer-fr-test auditlogproducer-fr-test=ccicreativeuk.azurecr.io/cci-audit-log-producer:$TRAVIS_BUILD_NUMBER
elif [ $TRAVIS_BRANCH = "release" ];
then
  mkdir ${HOME}/.kube
  cp .kube/config_preprod ${HOME}/.kube/config

  # Fill out missing params in kubectl config file
  kubectl config set clusters.cci-cms-preprod.certificate-authority-data "$KUBE_CLUSTER_CERTIFICATE_PREPROD"
  kubectl config set users.cci-cms-preprod-admin.client-certificate-data "$KUBE_CLIENT_CERTIFICATE_PREPROD"
  kubectl config set users.cci-cms-preprod-admin.client-key-data "$KUBE_CLIENT_KEY_PREPROD"
  #update the image
  kubectl set image -n cms-fr deployment/auditlogproducer-fr-preprod auditlogproducer-fr-preprod=ccicreativeuk.azurecr.io/cci-audit-log-producer:$TRAVIS_BUILD_NUMBER
else
  exit 1
fi
