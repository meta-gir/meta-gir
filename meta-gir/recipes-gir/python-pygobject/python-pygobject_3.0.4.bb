SUMMARY = "Python GObject bindings"
SECTION = "devel/python"
LICENSE = "LGPLv2.1"
LIC_FILES_CHKSUM = "file://COPYING;md5=a916467b91076e631dd8edb7424769c7"

DEPENDS = "glib-2.0 g-ir-core libffi libgirepository python python-pygobject-native zlib"
DEPENDS_class-native = "glib-2.0-native g-ir-tools-native"

inherit autotools pkgconfig gnomebase pythonnative

SRCNAME="pygobject"
SRC_URI = " \
    http://ftp.gnome.org/pub/GNOME/sources/${SRCNAME}/${@gnome_verdir("${PV}")}/${SRCNAME}-${PV}.tar.xz \
    file://fix-configure.patch \
    file://disable-tests.patch \
"
SRC_URI[md5sum] = "0cbcda00d9276f78040d361d1611a6a0"
SRC_URI[sha256sum] = "f457b1d7f6b8bfa727593c3696d2b405da66b4a8d34cd7d3362ebda1221f0661"

S = "${WORKDIR}/${SRCNAME}-${PV}"

EXTRA_OECONF += "--enable-introspection=yes --disable-cairo --disable-glibtest"
TARGET_CFLAGS += "-I${STAGING_INCDIR}/gobject-introspection-1.0"
TARGET_CFLAGS += "-I${STAGING_LIBDIR}/libffi-3.0.11/include"

do_configure_prepend () {
    sed -i -e "s|PYTHON=/usr/bin/python|PYTHON=${PYTHON}|" \
        ${S}/configure.ac
}

PACKAGES += "${PN}-lib"

FILES_${PN} = "${libdir}/python*"
FILES_${PN}-lib = "${libdir}/lib*.so.*"
FILES_${PN}-dev += "${bindir} ${datadir}"
FILES_${PN}-dbg += "${libdir}/python*/*/gi/.debug ${libdir}/python*/*/gi/_glib/.debug ${libdir}/python*/*/gi/_gobject/.debug"


BBCLASSEXTEND = "native"
