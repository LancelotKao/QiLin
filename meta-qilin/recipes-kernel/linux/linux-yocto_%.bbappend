FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}:"

inherit kernel

#PREFERRED_PROVIDER_virtual/kernel ?= "linux-yocto"
PREFERRED_VERSION_linux-yocto = "6.1%"
KRANCH:qilin ?= "v6.1/standard/base"
#PREFERRED_VERSION_linux-yocto = "5.15%"
#KBRANCH:qilin ?= "v5.15/standard/base"

IMAGE_ROOTFS_EXTRA_SPACE:append = "${@bb.utils.contains("DISTRO_FEATURES", "systemd", " + 4096", "" ,d)}"

KMACHINE:qilin ?= "common-pc-64"

#SRCREV_machine:qilin ?= "423e1996694b61fbfc8ec3bf062fc6461d64fde1"
#SRCREV_machine:qilin ?= "581dc1aa2f340fff2cc010067257185fa2c993f9"

COMPATIBLE_MACHINE = "qilin"
#COMPATIBLE_MACHINE:qilin = "genericx86-64"

#LINUX_VERSION:qilin = "6.1.25"
#LINUX_VERSION:qilin = "5.15.108"

KCONFIG_MODE="--alldefconfig"

#KERNEL_FEATURES += "features/vdso/vdso.scc"

INSANE_SKIP:${PN} += "already-stripped"

# Common Kernel config and patches
SRC_URI:append = " \
    file://qilin.cfg \
"

do_install:append () {
    install -d ${D}${libdir}/modules/${KERNEL_VERSION}/vdso

    install -m 644 -g root -o root ${B}/arch/x86/entry/vdso/vdso64.so ${D}${libdir}/modules/${KERNEL_VERSION}/vdso/
}

do_rootfs:append () {
    # Add the vdso files to the root file system
    install -d ${IMAGE_ROOTFS}/${libdir}/modules/${KERNEL_VERSION}/vdso/

    install -m 644 ${B}/arch/x86/entry/vdso/vdso64.so ${IMAGE_ROOTFS}/${libdir}/modules/${KERNEL_VERSION}/vdso/
}

PACKAGES += "${PN}"
FILES:${PN} = "${libdir}/modules/${KERNEL_VERSION}/vdso/vdso64.so"
