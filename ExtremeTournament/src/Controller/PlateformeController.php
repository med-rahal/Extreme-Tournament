<?php

namespace App\Controller;

use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;

class PlateformeController extends AbstractController
{
    /**
     * @Route("/About", name="app_About")
     */
    public function index(): Response
    {
        return $this->render('plateforme/index.html.twig', [
            'controller_name' => 'PlateformeController',
        ]);
    }

    /**
     * @Route("/Gallery", name="app_Gallery")
     */
    public function indexGallery(): Response
    {
        return $this->render('plateforme/Gallery.html.twig', [
            'controller_name' => 'PlateformeController',
        ]);
    }

    /**
     * @Route("/Partners", name="app_Partners")
     */
    public function indexPartners(): Response
    {
        return $this->render('plateforme/Partners.html.twig', [
            'controller_name' => 'PlateformeController',
        ]);
    }

    /**
     * @Route("/Contact", name="app_Contact")
     */
    public function indexContact(): Response
    {
        return $this->render('plateforme/Contact.html.twig', [
            'controller_name' => 'PlateformeController',
        ]);
    }




}
