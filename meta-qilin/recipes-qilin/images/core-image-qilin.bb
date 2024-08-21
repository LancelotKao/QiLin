DESCRIPTION = "QiLin system that provide a kernel and rootfs that runs on ramdisk"

SUMMARY = "Fii Diagnostic QiLin Systems"

#IMAGE_FEATURES += "splash ssh-server-openssh package-management"
# Do not pollute the initrd image with rootfs features
IMAGE_LINGUAS = " "
IMAGE_FEATURES += "empty-root-password ssh-server-openssh package-management"
#IMAGE_INSTALL += "vdso libdbi-perl"
#IMAGE_INSTALL = "\
#    packagegroup-core-boot \
#    packagegroup-core-full-cmdline \
#    ${CORE_IMAGE_EXTRA_INSTALL} \
#    "

inherit qilin-common bash-completion

#RDEPENDS:${PN} = "udev"

VIRTUAL-RUNTIME_dev_manager ?= "udev"

PACKAGE_INSTALL = " \
    packagegroup-core-boot \
    dropbear bash bash-completion \
    kernel-modules \
    base-passwd \
    ${VIRTUAL-RUNTIME_base-utils} \
    ${VIRTUAL-RUNTIME_dev_manager} \
    ${ROOTFS_BOOTSTRAP_INSTALL} \
    ${QILIN_COMMON_IMAGE_INSTALL} \
    ${QILIN_INTEL_IMAGE_INSTALL} \
    "

#PACKAGE_INSTALL = "packagegroup-core-boot dropbear bash bash-completion"
#PACKAGE_INSTALL:append = " kernel-modules"
#PACKAGE_INSTALL:append = " ${VIRTUAL-RUNTIME_base-utils} ${VIRTUAL-RUNTIME_dev_manager}"
#PACKAGE_INSTALL:append = " ${VIRTUAL-RUNTIME_base-utils}"
#PACKAGE_INSTALL:append = " base-passwd ${ROOTFS_BOOTSTRAP_INSTALL}"
#PACKAGE_INSTALL:append = " ${QILIN_COMMON_IMAGE_INSTALL}"
#PACKAGE_INSTALL:append = " ${QILIN_INTEL_IMAGE_INSTALL}"


#export IMAGE_BASENAME = "${MLPREFIX}core-image-qilin-initrd"

LICENSE = "MIT"

# don't actually generate an image, just the artifacts needed for one
IMAGE_FSTYPES = "${INITRAMFS_FSTYPES}"

inherit core-image

IMAGE_ROOTFS_SIZE ?= "8192"
IMAGE_ROOTFS_EXTRA_SPACE:append = "${@bb.utils.contains("DISTRO_FEATURES", "systemd", " + 4096", "", d)}"

# Use the same restriction as initramfs-module-install
COMPATIBLE_HOST = '(x86_64.*|i.86.*|arm.*|aarch64.*)-(linux.*|freebsd.*)'
