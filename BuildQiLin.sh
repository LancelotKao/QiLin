#!/bin/bash

base_dir=$(pwd)
build_folder="QiLin"

if [ -d "$build_folder" ]; then
    echo "Directory ${build_folder} exists."
    rm -rf "$build_folder"
fi

if [ ! -d "$build_folder" ]; then
    mkdir "$build_folder"
fi

cd "$build_folder"

# Download source code
echo " Getting Poky github folder"
git clone https://git.yoctoproject.org/poky

echo " Getting Meta-openembedded github folder"
git clone https://git.openembedded.org/meta-openembedded

echo " Getting Intel board support common layer (official)"
git clone https://git.yoctoproject.org/meta-intel

echo " Getting AMD board support common layer (official)"
git clone https://git.yoctoproject.org/meta-amd

echo " Getting AArch64 (64-bit ARM) architecture support"
git clone https://git.linaro.org/openembedded/meta-linaro.git

echo " Getting General layer for Arm recipes"
git clone https://git.linaro.org/openembedded/meta-aarch64.git

echo " Getting QiLin folder"
git clone http://10.18.118.31/fii_diag/meta-qilin

# Perpare the enviornment
export TEMPLATECONF="${base_dir}/${build_folder}/meta-qilin/conf/templates/default"
ln -s poky meta-poky
source poky/oe-init-build-env

# build the image
bitbake core-image-qilin

